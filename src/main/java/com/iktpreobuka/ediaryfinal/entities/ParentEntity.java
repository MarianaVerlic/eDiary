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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity(name = "parent")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Table(name = "parent")
@JsonView(value = {Views.Admin.class})
public class ParentEntity extends UserEntity{
	
	
	
	
	@Column
	private String email;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StudentEntity> children;

	public ParentEntity() {
		super();
	}

	public ParentEntity(Integer id, String username, String password, String passwordConfirmed, String firstName, String lastName,
			RoleEntity role, Boolean deleted, String email, List<StudentEntity> children) {
		super(id, username, password, passwordConfirmed, firstName, lastName, role, deleted);
		this.email = email;
		this.children = children;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<StudentEntity> getChildren() {
		return children;
	}

	public void setChildren(List<StudentEntity> children) {
		this.children = children;
	}

	
	
}

	

	
