package com.niit.LoginBackend.contoller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.LoginBackend.dao.RegistrationDAO;
import com.niit.LoginBackend.model.*;

@RestController
public class UserProfileController {

	@Autowired
	RegistrationDAO service;

	private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	@RequestMapping(value = "/adduserprofile/", method = RequestMethod.POST)
	//Method Tested
	public ResponseEntity<Registration> createUserProfile(@RequestBody Registration userprofile)
	{
		log.debug("calling => createUserProfile() method");

		if(service.checkUserEmail(userprofile.getUseremail())==true)
		{
			log.debug("error in calling => createUserProfile() method");
			return new ResponseEntity<Registration>(userprofile, HttpStatus.CONFLICT);
		}
		else
		{
			userprofile.setCurrentrole("Role_User");
			log.debug("hiii"+userprofile.getCurrentrole());

			service.saveUserProfile(userprofile);
			log.debug("Update new user type");
			return new ResponseEntity<Registration>(userprofile, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<List<Registration>> listAllUsers()	{

		log.debug("calling => UserProfile() method");
		List<Registration> lsts = service.getAllUsers();
		log.debug("total records:" + lsts.size());
		if(lsts.isEmpty()){
			return new ResponseEntity<List<Registration>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Registration>>(lsts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserbyemail/{useremail:.*}", method = RequestMethod.GET)
	//Method Tested
	public ResponseEntity<Registration> getuserbyemail(@PathVariable("useremail") String useremail)	{
		log.debug("calling => getuserbyemail() method " + useremail);
		Registration usrprofile = service.getUserProfileByEmail(useremail);
		if(usrprofile==null)
		{
			return new ResponseEntity<Registration>(HttpStatus.NO_CONTENT);
		}
		log.debug("Record :" + usrprofile.getUseremail());
		return new ResponseEntity<Registration>(usrprofile, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Registration> authenticate(@RequestBody Registration userprofile, HttpSession session)
	{
		log.debug("calling => udpateUserProfile() method");
		Registration userobj = service.authenticate(userprofile.getUseremail(), userprofile.getPassword());		
		if(userobj==null){
			userobj = new Registration();
		}
		else
		{
			session.setAttribute("profile", userobj);
			session.setAttribute("loggeduser", userobj.getUseremail());
		}
		return new ResponseEntity<Registration>(userobj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Registration> logoutuser(HttpSession session)	{
		String loggeduser = (String)session.getAttribute("loggeduser");
		session.invalidate();
		log.debug("Logged user :" + loggeduser);
		return new ResponseEntity<Registration>(HttpStatus.OK);
	}
}