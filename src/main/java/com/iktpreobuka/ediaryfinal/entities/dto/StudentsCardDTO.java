package com.iktpreobuka.ediaryfinal.entities.dto;

//import javax.persistence.GeneratedValue;

//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;

public class StudentsCardDTO {
	

	private StudentEntity student;
	
	@JsonProperty(value = "absences")
	private Integer absences;
	
	@JsonProperty(value = "notes")
	private String notes;
	
	

	public StudentsCardDTO(StudentEntity student, Integer absences, String notes) {
		super();
		this.student = student;
		this.absences = absences;
		this.notes = notes;
		
	}

	public StudentsCardDTO() {
		super();
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public Integer getAbsences() {
		return absences;
	}

	public void setAbsences(Integer absences) {
		this.absences = absences;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	
	
	

}	