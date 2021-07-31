package com.iktpreobuka.ediaryfinal.entities;

import java.time.LocalDate;
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
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "teacher")
public class TeacherEntity extends UserEntity{

	
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="teacherSubject")
	private TeacherSubjectEntity teacherSubject;

	public TeacherEntity() {
		super();
	}	
	@Column
	private Boolean deleted;
	
	@Column
	private String email;

	public TeacherEntity(Integer id, String username, String password, String passwordConfirmed, String firstName, String lastName,
			RoleEntity role, Boolean deleted, TeacherSubjectEntity teacherSubject, Boolean deleted2, String email) {
		super(id, username, password, passwordConfirmed, firstName, lastName, role, deleted);
		this.teacherSubject = teacherSubject;
		deleted = deleted2;
		this.email = email;
	}

	public TeacherSubjectEntity getTeacherSubject() {
		return teacherSubject;
	}

	public void setTeacherSubject(TeacherSubjectEntity teacherSubject) {
		this.teacherSubject = teacherSubject;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}