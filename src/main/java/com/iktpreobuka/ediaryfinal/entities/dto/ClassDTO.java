package com.iktpreobuka.ediaryfinal.entities.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

	
	
	public class ClassDTO {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@NotBlank(message = "Year must not be null.")
		@Pattern(regexp = "^[1-9{n}]$", message = "Year must be in range of [1-8].")
		private String year;
		
		@NotBlank(message = "Class number must not be null.")
		@Pattern(regexp = "^[1-9{n}]$", message = "Class number must be in range of [1-4].")
		private String classNr;
		
		@JsonProperty(value = "schoolYear")
		private String schoolYear;
		
	

		public ClassDTO() {
			super();
		}


		


		public ClassDTO(String year, String classNr, String schoolYear) {
			super();
			this.year = year;
			this.classNr = classNr;
			this.schoolYear = schoolYear;
		}


		


		public Integer getId() {
			return id;
		}





		public void setId(Integer id) {
			this.id = id;
		}





		public String getYear() {
			return year;
		}


		public void setYear(String year) {
			this.year = year;
		}


		public String getClassNr() {
			return classNr;
		}


		public void setClassNr(String classNr) {
			this.classNr = classNr;
		}


		public String getSchoolYear() {
			return schoolYear;
		}


		public void setSchoolYear(String schoolYear) {
			this.schoolYear = schoolYear;
		}
		
		


	}