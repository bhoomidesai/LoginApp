package com.niit.LoginBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.LoginBackend.dao.*;
import com.niit.LoginBackend.model.*;

public class UserProfileTest 
{
	private static 	AnnotationConfigApplicationContext context;
	private static RegistrationDAO userProfileDao;
	private Registration userProfile;

	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration_backend");
		context.refresh();
		
		userProfileDao =  (RegistrationDAO)context.getBean("userProfileDao",RegistrationDAO.class);
		//userProfile =(UserProfile)context.getBean("userProfile");
	}
	
	@Test
	public void createUser()
	{	
		userProfile = new Registration();
		userProfile.setFstname("abc");
		userProfile.setUseremail("testuser@niit.com");
		userProfile.setPassword("Test@123");
		userProfile.setCurrentrole("Role_User");

		assertEquals("created newUser", userProfileDao.saveUserProfile(userProfile), true);	
	}
	
	@Test
	public void getAllUsers()
	{
		assertEquals("users received from DB", 8,userProfileDao.getAllUsers().size());
	}
	
	@Test
	public void getUserProfileByEmail()
	{
		userProfile = userProfileDao.getUserProfileByEmail("bhoomi@niit.com");
		assertEquals("user get by mail from DB", userProfileDao.getUserProfileByEmail("bhoomi@niit.com").getUseremail(),userProfile.getUseremail());
	}
	
	@Test
    public void isValidUser(){
		userProfile=new Registration();
		userProfile.setUseremail("admin@niit.com");
		userProfile.setPassword("Admin@123");
		Registration u = userProfileDao.authenticate(userProfile.getUseremail(),userProfile.getPassword());
                    assertEquals("isValidUser",userProfileDao.authenticate(userProfile.getUseremail(),userProfile.getPassword()).getUseremail(),u.getUseremail());
    }
	
}
