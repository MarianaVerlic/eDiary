package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;

public interface StudentsCardRepository extends CrudRepository<StudentsCardEntity, Integer> {
	
	
	

	public Optional<StudentsCardEntity> findById(Integer id);
	
	public List<StudentsCardEntity> findByStudent(StudentEntity student);
	
	public StudentsCardEntity findByTeacherSubjectClass(TeacherSubjectClassEntity teachSubClass);

	public List<StudentsCardEntity> findByStudentId(Integer studentId);
	
	public StudentsCardEntity findByTeacherSubjectClassIdAndStudentId(Integer teacherSubClassId, Integer studentId);

	}



