package com.iktpreobuka.ediaryfinal.entities.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.security.Views;


public class TeacherDTO {
	
	
	@NotBlank(message = "Username must not be null")
	@Size(min = 5, max = 15, message = "Username must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username can contain only alphanumeric characters.")
	private String username;
	
	@NotBlank(message = "Password must not be null")
	@Size(min = 5, max = 15, message = "Password must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.")
	private String password;
	
	@NotBlank(message = "Confirmed password must not be null.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.")
	private String passwordConfirmed;
	
	@NotBlank(message = "First name must not be null.")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.")
	private String firstname;
	
	@NotBlank(message = "Last name must not be null")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.")
	private String lastname;
	
	@NotBlank(message = "Email must be provided.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", 
	message="Email is not valid.")
    private String email;
	
		
	@JsonProperty(value="Subject")
	private TeacherSubjectEntity subject;

	public TeacherDTO() {
		super();
	}
	@Transient
	private String subjectName;
	
	
	

	public TeacherDTO(
			@NotBlank(message = "Username must not be null") @Size(min = 5, max = 15, message = "Username must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username can contain only alphanumeric characters.") String username,
			@NotBlank(message = "Password must not be null") @Size(min = 5, max = 15, message = "Password must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.") String password,
			@NotBlank(message = "Confirmed password must not be null.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.") String passwordConfirmed,
			@NotBlank(message = "First name must not be null.") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.") String firstname,
			@NotBlank(message = "Last name must not be null") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.") String lastname,
			@NotBlank(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			TeacherSubjectEntity subject) {
		super();
		this.username = username;
		this.password = password;
		this.passwordConfirmed = passwordConfirmed;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.subject = subject;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public TeacherSubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(TeacherSubjectEntity subject) {
		this.subject = subject;
	}

	public TeacherDTO(
			@NotBlank(message = "First name must not be null.") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.") String firstname,
			@NotBlank(message = "Last name must not be null") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.") String lastname,
			@NotBlank(message = "Email must be provided.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			String subjectName) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.subjectName = subjectName;
	}

	
	}

	
	
	

	
	