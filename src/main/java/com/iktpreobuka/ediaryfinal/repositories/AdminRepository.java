package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	public Optional<AdminEntity> findById(Integer id);
	
	public List<AdminEntity> findAll();
	
	public Boolean existsByUsername(String username);
	
	public AdminEntity findByUsername(String username);
	
	
	
}
