package com.iktpreobuka.ediaryfinal.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity
@Table(name = "subject")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@NotBlank(message = "Subject name made must be provided.")
	private String name;
	
	@Column
	@NotNull(message = "Class load per week must be provided.")
	private Integer classLoad;
	
	@OneToMany(mappedBy="subject", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<TeacherSubjectEntity> teacherSubject;
	
	
	public SubjectEntity(String name, Integer classLoad) {
		super();
		this.name = name;
		this.classLoad = classLoad;
	}



	public SubjectEntity() {
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public List<TeacherSubjectEntity> getTeacherSubject() {
		return teacherSubject;
	}



	public void setTeacherSubject(List<TeacherSubjectEntity> teacherSubject) {
		this.teacherSubject = teacherSubject;
	}



	
	
	
	

}

	