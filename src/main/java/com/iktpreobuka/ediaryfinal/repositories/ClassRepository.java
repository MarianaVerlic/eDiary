package com.iktpreobuka.ediaryfinal.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;


public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
	
	public Optional<ClassEntity> findById(Integer id);
	
	public Boolean existsByClassNrAndYear(String classNr, String year);
	
	
	
	
}
	
	

