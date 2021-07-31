package com.iktpreobuka.ediaryfinal.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity(name = "class")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class ClassEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String year;
	
	@Column
	private String classNr;
	
	@Column
	private String schoolYear;
	
	
	@OneToMany(mappedBy="schoolClass", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<StudentEntity> students;
	
	@OneToMany(mappedBy="schoolClass", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<TeacherSubjectClassEntity> teacherSubjectClass;
	
	@Column
	private Boolean deleted;
	
	public ClassEntity() {
		super();
	}

	public ClassEntity(Integer id, @NotBlank(message = "Year must be provided.") String year,
			@NotBlank(message = "Class number must be provided.") String classNr,
			@NotBlank(message = "School year must be provided.") String schoolYear, List<StudentEntity> students,
			List<TeacherSubjectClassEntity> teacherSubjectClass, Boolean deleted) {
		super();
		this.id = id;
		this.year = year;
		this.classNr = classNr;
		this.schoolYear = schoolYear;
		this.students = students;
		this.teacherSubjectClass = teacherSubjectClass;
		this.deleted = deleted;
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

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
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
	
}
	




	