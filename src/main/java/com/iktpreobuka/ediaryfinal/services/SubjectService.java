package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;

public interface SubjectService {
	
	public SubjectEntity createSubject(SubjectDTO subject);

	public SubjectEntity changeSubject(Integer id, SubjectEntity subject);
	
	public Iterable<SubjectEntity> findAllSubject();
	
	public Optional<SubjectEntity> findById(Integer id);
	
	public SubjectEntity addNewSubject(SubjectEntity newSubject);
	
	public SubjectEntity deleteSubject(Integer id);
	
	boolean ifExists(Integer id);
	
	boolean ifExistsName(String name);
	
	
	
}