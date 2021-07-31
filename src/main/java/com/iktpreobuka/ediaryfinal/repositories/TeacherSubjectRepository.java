package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;

public interface TeacherSubjectRepository extends CrudRepository<TeacherSubjectEntity, Integer> {
	
	
	public Optional<TeacherSubjectEntity> findById(Integer id);
	
	public Optional<TeacherSubjectEntity> findByTeachers(TeacherEntity teacher);
	
	public Optional<TeacherSubjectEntity> findBySubject(SubjectEntity subject);
	
	public Optional<TeacherSubjectEntity> findByTeachersAndSubject(TeacherEntity teacher, SubjectEntity subject);
	
	public List<TeacherSubjectEntity>findBySubjectId(Integer subjectId);
	
	public Boolean existsByTeachersAndSubject(TeacherEntity teacher, SubjectEntity subject);
	
	
	
}


