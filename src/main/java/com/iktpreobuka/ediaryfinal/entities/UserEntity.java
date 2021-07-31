package com.iktpreobuka.ediaryfinal.entities;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
		private Integer id;
		
		@Column
		private String username;
		
		@Column
		private String password;
		
		@Column
		private String passwordConfirmed;
		
		@Column
		private String firstName;
		
		@Column
		private String lastName;
		
		@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
		@JoinColumn(name = "role")
		private RoleEntity role;
		
		@Column
		private Boolean deleted;
		
	
		private String email;
		
		public UserEntity(Integer id, String username, String password, String passwordConfirmed, String firstName,
				String lastName, RoleEntity role, Boolean deleted) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.passwordConfirmed = passwordConfirmed;
			this.firstName = firstName;
			this.lastName = lastName;
			this.role = role;
			this.deleted = deleted;
			
		}

		public UserEntity() {
			super();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordConfirmed() {
			return passwordConfirmed;
		}

		public void setPasswordConfirmed(String passwordConfirmed) {
			this.passwordConfirmed = passwordConfirmed;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public RoleEntity getRole() {
			return role;
		}

		public void setRole(RoleEntity role) {
			this.role = role;
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