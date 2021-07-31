package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;

public interface ClassService {

	public Iterable<ClassEntity> getAll();
	
	public Optional<ClassEntity> findById(Integer id);
	
	public ClassEntity addNew(ClassEntity newSchoolClass);
	
	public ClassEntity update(Integer id, ClassEntity newSchoolClass);
	
	public ClassEntity delete(Integer id);
	
	public boolean ifExistsId(Integer Id);
	
	public ClassEntity createNew(ClassDTO newClassDTO);
	
	public Boolean isActive(Integer id);
	
	
	
	
}

