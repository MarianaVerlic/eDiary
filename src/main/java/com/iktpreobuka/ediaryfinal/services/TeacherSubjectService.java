package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;

public interface TeacherSubjectService {

	public List<TeacherSubjectEntity> findBySubject(Integer subjectId);
	
	public TeacherSubjectEntity addNew( TeacherSubjectEntity newTS);
	
	public TeacherSubjectEntity delete(Integer id);
	
	public Optional<TeacherSubjectEntity> findByTeacher(TeacherEntity teacher);

	
}
