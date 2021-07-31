package com.iktpreobuka.ediaryfinal.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.security.Views;


public class StudentDTO {
	
	
	@NotBlank(message = "Username must not be null")
	@Size(min = 5, max = 15, message = "Username must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username can contain only alphanumeric characters.")
	private String username;
	
	@NotBlank(message = "Password must not be null")
	@Size(min = 5, max = 15, message = "Password must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.")
	private String password;
	
	@NotBlank(message = "Password confirmed must not be null.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.")
	private String passwordConfirmed;
	
	@NotBlank(message = "First name must not be null.")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.")
	private String firstName;
	
	@NotBlank(message = "Last name must not be null")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.")
	private String lastName;
	

	
	private Integer parentId;

	
	private Integer classId;

	public StudentDTO(
			@NotBlank(message = "Username must not be null") @Size(min = 5, max = 15, message = "Username must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username can contain only alphanumeric characters.") String username,
			@NotBlank(message = "Password must not be null") @Size(min = 5, max = 15, message = "Password must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.") String password,
			@NotBlank(message = "Password confirmed must not be null.") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password can contain only alphanumeric characters.") String passwordConfirmed,
			@NotBlank(message = "First name must not be null.") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.") String firstName,
			@NotBlank(message = "Last name must not be null") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.") @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.") String lastName,
			Integer parentId, Integer classId) {
		super();
		this.username = username;
		this.password = password;
		this.passwordConfirmed = passwordConfirmed;
		this.firstName = firstName;
		this.lastName = lastName;
		this.parentId = parentId;
		this.classId = classId;
	}

	public StudentDTO() {
		super();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	
	

		}