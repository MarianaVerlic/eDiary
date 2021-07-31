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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "student")
public class StudentEntity extends UserEntity{
	
	
	
		
		@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
		@JoinColumn(name = "parent")
		private ParentEntity parent;
		
		@OneToMany(mappedBy="student", cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
		@JsonIgnore
		private List<StudentsCardEntity> studentsCards;
		
		@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
		@JoinColumn(name="schoolClass")
		private ClassEntity schoolClass;

		public StudentEntity(Integer id, String username, String password, String passwordConfirmed,String firstName, String lastName,
				RoleEntity role, Boolean deleted, ParentEntity parent, List<StudentsCardEntity> studentsCards,
				ClassEntity schoolClass) {
			super(id, username, password, passwordConfirmed,  firstName, lastName, role, deleted);
			this.parent = parent;
			this.studentsCards = studentsCards;
			this.schoolClass = schoolClass;
		}

		
		
		public StudentEntity() {
			super();
		}



		



		



		public ParentEntity getParent() {
			return parent;
		}

		public void setParent(ParentEntity parent) {
			this.parent = parent;
		}

		public List<StudentsCardEntity> getStudentsCards() {
			return studentsCards;
		}

		public void setStudentsCards(List<StudentsCardEntity> studentsCards) {
			this.studentsCards = studentsCards;
		}

		public ClassEntity getSchoolClass() {
			return schoolClass;
		}

		public void setSchoolClass(ClassEntity schoolClass) {
			this.schoolClass = schoolClass;
		}
		
		
}
	
	
	