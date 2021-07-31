package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.RoleEntity;

public interface RoleService {
	
	public Iterable<RoleEntity> getAll();
	
	public RoleEntity getById(Integer id);
	
	public RoleEntity save(RoleEntity role);
	
	public RoleEntity delete(Integer id);
	
	public RoleEntity update(Integer id, RoleEntity newRole);
	
	public Optional<RoleEntity> findByRoleName(String name);
	 
	public boolean ifExists(String name);
	
	public RoleEntity create(RoleEntity newRole);
	 
	 
}


