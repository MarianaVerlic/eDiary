package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.iktpreobuka.ediaryfinal.entities.MarkEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;

public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {
	
	public List<MarkEntity> findByStudentsCardId(Integer studentsCardId);

		
	public List<MarkEntity> findByStudentsCardTeacherSubjectClassId(Integer teacherSubjectClassId);
		
}
