package com.iktpreobuka.ediaryfinal.entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.security.Views;

@Entity
@Table(name = "role")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
	public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@Column(name = "role_name", unique = true)
	private String roleName;

	@OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEntity> users = new ArrayList<>();


	public RoleEntity() {
		super();
	}


	

	public RoleEntity(Integer id, String roleName, List<UserEntity> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<UserEntity> getUsers() {
		return users;
	}


	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
	

	
	
}


