package com.iktpreobuka.ediaryfinal.entities;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;


@Entity
@Table(name = "students_card")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class StudentsCardEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="student")
	private StudentEntity student;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="teacherSubjectClass")
	protected TeacherSubjectClassEntity teacherSubjectClass;
	
	
	@OneToMany(mappedBy="studentsCard", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<MarkEntity>marks=new ArrayList<>();
	
	@Column
	private Integer absences;
	
	@Column
	private String notes;
	
	@Column
	private Boolean deleted;
	
	

	public StudentsCardEntity() {
		super();
	}

	public StudentsCardEntity(Integer id, StudentEntity student, TeacherSubjectClassEntity teacherSubjectClass,
			List<MarkEntity> marks, Integer absences, String notes, Boolean deleted) {
		super();
		this.id = id;
		this.student = student;
		this.teacherSubjectClass = teacherSubjectClass;
		this.marks = marks;
		this.absences = absences;
		this.notes = notes;
		this.deleted = deleted;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public TeacherSubjectClassEntity getTeacherSubjectClass() {
		return teacherSubjectClass;
	}

	public void setTeacherSubjectClass(TeacherSubjectClassEntity teacherSubjectClass) {
		this.teacherSubjectClass = teacherSubjectClass;
	}

	public List<MarkEntity> getMarks() {
		return marks;
	}

	public void setMarks(List<MarkEntity> marks) {
		this.marks = marks;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	
	
	}
	
	


	