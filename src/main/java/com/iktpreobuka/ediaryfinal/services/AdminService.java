package com.iktpreobuka.ediaryfinal.services;

import java.util.List;

import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.AdminEntity;


public interface AdminService {
	
	public Iterable<AdminEntity> getAll();
	
	public Optional<AdminEntity> findById(Integer id);
	
	public AdminEntity add(AdminEntity newAdmin);
	
	public AdminEntity update(Integer id, AdminEntity newAdmin);
	
	public AdminEntity delete(Integer id);
	
	public boolean ifExists(Integer id);
	
	public List<String> getAllUsernames();
	
	public Integer findIdByUsername(String username);

	public Boolean isActive(Integer id);
	
	
}


