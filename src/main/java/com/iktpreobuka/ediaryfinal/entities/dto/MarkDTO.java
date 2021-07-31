package com.iktpreobuka.ediaryfinal.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.enums.MarkTypes;
import com.iktpreobuka.ediaryfinal.security.Views;


public class MarkDTO {
	
	
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Mark type null or invalid. Accepted values: [FIRST_QUARTER, SECOND_QUARTER, FIRST_SEMESTER, "
			+ "THIRD_QUARTER, FOURTH_QUARTER, SECOND_SEMESTER, FINAL].")
	private MarkTypes markType;
	
	
	@NotNull(message="Mark value must be provided.")
    @Min(value = 1, message = "Mark value must be equal or greater than {value}.")
	@Max(value = 5, message = "Mark value must be equal or lesser than than {value}.")
	private Integer markValue;
	
	@JsonProperty(value = "studentsCard")
	private StudentsCardEntity studentsCard;
	

	public MarkDTO() {
		super();
	}


	

	public MarkDTO(MarkTypes markType,
			@NotNull(message = "Mark value must be provided.") @Min(value = 1, message = "Mark value must be equal or greater than {value}.") @Max(value = 5, message = "Mark value must be equal or lesser than than {value}.") Integer markValue,
			StudentsCardEntity studentsCard) {
		super();
		this.markType = markType;
		this.markValue = markValue;
		this.studentsCard = studentsCard;
	}


	

	public MarkTypes getMarkType() {
		return markType;
	}


	public void setMarkType(MarkTypes markType) {
		this.markType = markType;
	}


	public Integer getMarkValue() {
		return markValue;
	}


	public void setMarkValue(Integer markValue) {
		this.markValue = markValue;
	}


	public StudentsCardEntity getStudentsCard() {
		return studentsCard;
	}


	public void setStudentsCard(StudentsCardEntity studentsCard) {
		this.studentsCard = studentsCard;
	}


}