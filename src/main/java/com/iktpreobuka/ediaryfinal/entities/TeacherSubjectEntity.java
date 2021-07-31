package com.iktpreobuka.ediaryfinal.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;


@Entity
@Table(name = "teacherSubject")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class TeacherSubjectEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String schoolYear;
	
	@OneToMany(mappedBy="teacherSubject", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<TeacherEntity> teachers;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="subject")
	private SubjectEntity subject;
	
	@OneToMany(mappedBy="teacherSubject", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List <TeacherSubjectClassEntity> teacherSubjectClass;
	
	
	public List<TeacherEntity> getTeachers() {
		return teachers;
	}
	
	public Boolean deleted;


	public TeacherSubjectEntity(Integer id, String schoolYear, List<TeacherEntity> teachers, SubjectEntity subject,
			List<TeacherSubjectClassEntity> teacherSubjectClass, Boolean deleted) {
		super();
		this.id = id;
		this.schoolYear = schoolYear;
		this.teachers = teachers;
		this.subject = subject;
		this.teacherSubjectClass = teacherSubjectClass;
		this.deleted = deleted;
	}


	public TeacherSubjectEntity() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getSchoolYear() {
		return schoolYear;
	}


	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}


	public SubjectEntity getSubject() {
		return subject;
	}


	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}


	public List<TeacherSubjectClassEntity> getTeacherSubjectClass() {
		return teacherSubjectClass;
	}


	public void setTeacherSubjectClass(List<TeacherSubjectClassEntity> teacherSubjectClass) {
		this.teacherSubjectClass = teacherSubjectClass;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	public void setTeachers(List<TeacherEntity> teachers) {
		this.teachers = teachers;
	}



}