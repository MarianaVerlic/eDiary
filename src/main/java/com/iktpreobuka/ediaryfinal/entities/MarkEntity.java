package com.iktpreobuka.ediaryfinal.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.entities.enums.MarkTypes;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity
@Table(name = "mark")
public class MarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    
	@Column
	@Enumerated(value = EnumType.STRING)
	private MarkTypes markType;
	
    @Column
	private Integer markValue;
    
 
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="studentsCard")
	private StudentsCardEntity studentsCard;
	
	
	@Column
	private Boolean deleted;
	
	
	public MarkEntity() {
		super();
	}


	public MarkEntity(Integer id, MarkTypes markType, Integer markValue, StudentsCardEntity studentsCard,
			Boolean deleted) {
		super();
		this.id = id;
		this.markType = markType;
		this.markValue = markValue;
		this.studentsCard = studentsCard;
		this.deleted = deleted;
	}


	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}


	