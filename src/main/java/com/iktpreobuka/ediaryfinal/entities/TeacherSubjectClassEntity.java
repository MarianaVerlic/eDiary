package com.iktpreobuka.ediaryfinal.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "teacher_subject_class")
public class TeacherSubjectClassEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="teacherSubject")
	protected TeacherSubjectEntity teacherSubject;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="schoolClass")
	protected ClassEntity schoolClass;
	
	@OneToMany(mappedBy="teacherSubjectClass", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	protected List<StudentsCardEntity>studentsCards=new ArrayList<>();
	
	public Boolean deleted;

	public TeacherSubjectClassEntity() {
		super();
	}

	public TeacherSubjectClassEntity(Integer id, TeacherSubjectEntity teacherSubject, ClassEntity schoolClass,
			List<StudentsCardEntity> studentsCards, Boolean deleted) {
		super();
		this.id = id;
		this.teacherSubject = teacherSubject;
		this.schoolClass = schoolClass;
		this.studentsCards = studentsCards;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeacherSubjectEntity getTeacherSubject() {
		return teacherSubject;
	}

	public void setTeacherSubject(TeacherSubjectEntity teacherSubject) {
		this.teacherSubject = teacherSubject;
	}

	public ClassEntity getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(ClassEntity schoolClass) {
		this.schoolClass = schoolClass;
	}

	public List<StudentsCardEntity> getStudentsCards() {
		return studentsCards;
	}

	public void setStudentsCards(List<StudentsCardEntity> studentsCards) {
		this.studentsCards = studentsCards;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}

	