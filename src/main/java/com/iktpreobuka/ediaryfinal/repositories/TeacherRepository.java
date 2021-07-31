package com.iktpreobuka.ediaryfinal.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {
	
	
	public Optional<TeacherEntity> findById(Integer id);
	
	public Boolean existsByUsername(String username);
	
	public TeacherEntity findByUsername(String username);

}
