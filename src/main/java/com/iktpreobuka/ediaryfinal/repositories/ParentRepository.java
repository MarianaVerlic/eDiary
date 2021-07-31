package com.iktpreobuka.ediaryfinal.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.AdminEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {
	
	public ParentEntity findByEmail(String email);
	
	public Optional<ParentEntity> findById(Integer id);
	
	public ParentEntity findByChildren(StudentEntity student);
	
	public ParentEntity findByUsername(String username);
}


