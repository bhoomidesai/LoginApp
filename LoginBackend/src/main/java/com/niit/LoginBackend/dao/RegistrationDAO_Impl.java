package com.niit.LoginBackend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.LoginBackend.model.Registration;

@EnableTransactionManagement
@Repository("userProfileDao")
public class RegistrationDAO_Impl implements RegistrationDAO {

	
	private static final Logger log = LoggerFactory.getLogger(RegistrationDAO_Impl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public RegistrationDAO_Impl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Registration> getAllUsers() {
		List<Registration> allUsers = null;
		try{
			
			log.debug("Method => getAllUsers() execution is starting");
			allUsers = sessionFactory.getCurrentSession().createQuery("FROM Registration").list();
			if(allUsers==null || allUsers.isEmpty()){
				log.debug("Record not found in Registration table");
			}
		}
		catch(HibernateException ex){
			log.debug("Fetch Error :" + ex.getMessage());
			ex.printStackTrace();
		}
		return allUsers;
	}

	@Override
	@Transactional
	public boolean saveUserProfile(Registration userprofile) {
		try
		{
			log.debug("Method => saveUserProfile() execution is starting");
			sessionFactory.getCurrentSession().saveOrUpdate(userprofile);
			sessionFactory.getCurrentSession().flush();
			return true;
		}
		catch(HibernateException ex){
			log.debug("Data Save Error :" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	
	@Override
	@Transactional
	public Registration getUserProfileByEmail(String useremail) {
		try
		{
			log.debug("Method => getMailByID() execution is starting : " + useremail);
			return (Registration) sessionFactory.getCurrentSession().get(Registration.class, useremail);
		}
		catch(HibernateException ex){
			log.debug("Data fetch Error :" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean checkUserEmail(String useremail) {
		String SQL = "FROM Registration where upper(useremail) = '" + useremail.toUpperCase() + "'";
		log.debug("SQL :" + SQL);
		List<Registration> obj = sessionFactory.getCurrentSession().createQuery(SQL).list();
		return obj.isEmpty() ? false : true;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Registration authenticate(String useremail, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Registration where useremail = '" +
	      useremail + "' and password = '" + password + "'");
		
		List<Registration> userprofile = query.list();
		if(userprofile != null && !userprofile.isEmpty()){
			return userprofile.get(0);
		}
		return null;
	}

}
