package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer> {
	
	
	public Optional<SubjectEntity> findById(Integer id);
	
	public List<SubjectEntity> findByTeacherSubjectTeachersId(Integer teacherId);
	
	public SubjectEntity findByName(String name);
	
	public Boolean existsByName(String name);

}
