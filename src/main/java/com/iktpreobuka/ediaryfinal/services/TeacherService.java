package com.iktpreobuka.ediaryfinal.services;

import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;

public interface TeacherService {
	
	public Iterable<TeacherEntity> getAll();
	
	public Optional<TeacherEntity> findById(Integer id);
	
	public TeacherEntity addNew(TeacherEntity teacher);
	
	public TeacherEntity delete(Integer id);
	
	public boolean ifExists(Integer id);
	
	public boolean ifExistsConectionTeacherSubject(TeacherEntity teacher, SubjectEntity subject);

	public Boolean isActive(Integer id)
	
	; 
}
