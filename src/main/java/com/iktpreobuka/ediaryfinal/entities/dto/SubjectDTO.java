package com.iktpreobuka.ediaryfinal.entities.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;


public class SubjectDTO {
	
	


	//@NotBlank(message = "Subject name must not be null.")
	@Size(min = 5, max = 30, message = "Subject name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z0-9\\s,]*$", message = "Invalid course name.")
	private String name;
	
	//@NotBlank(message = "Weekly class load must not be null.")
	@Min(value = 0, message = "Weekly class load cannot be less than zero.")
	@Max(value = 40, message = "Weekly class load cannot be above 40.")
	private Integer classLoad;
	
	private String teacher;
	

	public SubjectDTO() {
		super();
	}
	
	



	public SubjectDTO(String name, Integer classLoad) {
		super();
		this.name = name;
		this.classLoad = classLoad;
	}




	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	




	public Integer getClassLoad() {
		return classLoad;
	}





	public void setClassLoad(Integer classLoad) {
		this.classLoad = classLoad;
	}





	public String getTeacher() {
		return teacher;
	}





	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}





	public SubjectDTO(String name,Integer classLoad, String teacher) {
		super();
		this.name = name;
		this.classLoad = classLoad;
		this.teacher = teacher;
	}
	
	
}