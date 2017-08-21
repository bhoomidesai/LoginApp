package com.niit.LoginBackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
@Entity
@Table
public class Registration {

	@Id
	@Column
	@NotEmpty(message="Email ID should not be blank")
	@Email(message = "Please enter correct Email ID...")
	private String useremail;
	
	@Column
	@NotEmpty(message="First name should not be left blank")
	private String fstname;
	
	
	@Column
	@NotEmpty(message="Password should not be left blank")
	@Size(min = 6, max = 12, message = "Password should be match with between 6 to 15 characters")
	private String password;

	@Column
	@NotEmpty
	public String currentrole;
	
	public String getCurrentrole() {
		return currentrole;
	}

	public void setCurrentrole(String currentrole) {
		this.currentrole = currentrole;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getFstname() {
		return fstname;
	}

	public void setFstname(String fstname) {
		this.fstname = fstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
