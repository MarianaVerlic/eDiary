package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;

public interface StudentsCardService {
	
    public Iterable<StudentsCardEntity> getAll();
	
	public Optional<StudentsCardEntity> findById(Integer id);
	
	public StudentsCardEntity update(Integer id, StudentsCardEntity newStudent);
	
	public StudentsCardEntity delete(Integer id);
	
	public List<StudentsCardEntity> findByStudent(Integer studentId);
	
	public boolean ifExists(Integer id);

	public Boolean isActive(Integer id);
	
	
	
	 

}
