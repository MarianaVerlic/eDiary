package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;

public interface TeacherSubjectClassService {
	
	public List<TeacherSubjectClassEntity> findByTeacherSubject(Integer tsId);
	
	public TeacherSubjectClassEntity delete(Integer id);
	 

}
