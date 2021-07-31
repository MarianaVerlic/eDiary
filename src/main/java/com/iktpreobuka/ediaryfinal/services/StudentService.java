package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;

public interface StudentService {
	
	public Iterable<StudentEntity> getAll();
	
	public Optional<StudentEntity> findById(Integer id);
	
	public StudentEntity add(StudentEntity newStudent);
	
	public StudentEntity update(Integer id, StudentEntity newStudent);
	
	public StudentEntity delete(Integer id);
	
	public Optional<StudentEntity> findStudentsByClass(Integer classId);
	
	public List<StudentEntity> findStudentsByParent(Integer parentId);
	
	public boolean ifExists(Integer id);

	public Boolean isActive(Integer id);
	
	
	//public StudentEntity addStudentToClass(Integer classId, Integer studentId);
	
	//public StudentEntity deleteStudentsFromClass(Integer classId, Integer studentId);
	 

}
