package com.niit.LoginBackend.dao;

import java.util.List;

import com.niit.LoginBackend.model.Registration;


public interface RegistrationDAO {

	public List<Registration> getAllUsers();
	public boolean saveUserProfile(Registration userprofile);		
	public Registration getUserProfileByEmail(String useremail);
	boolean checkUserEmail(String useremail);
	public Registration authenticate(String useremail, String password);
		
}
