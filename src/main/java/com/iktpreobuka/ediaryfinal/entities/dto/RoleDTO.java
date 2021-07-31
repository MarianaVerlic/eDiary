package com.iktpreobuka.ediaryfinal.entities.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.security.Views;


public class RoleDTO {
	
	@Column(name="role_name")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Role null or invalid. Accepted values: [ADMIN, TEACHER, PARENT, STUDENT]")
	private	Roles role;
	
	
	
	

	public RoleDTO() {
		super();
	}

	public RoleDTO(Roles role) {
		super();
		this.role = role;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	
	
	
}
