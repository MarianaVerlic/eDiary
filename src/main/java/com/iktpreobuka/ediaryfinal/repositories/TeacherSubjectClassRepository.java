package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;

public interface TeacherSubjectClassRepository extends CrudRepository<TeacherSubjectClassEntity, Integer> {
	
	
	public Optional<TeacherSubjectClassEntity> findById(Integer id);
	
	public List<TeacherSubjectClassEntity> findByTeacherSubjectId(Integer teacherSubId);
	
	public Optional<TeacherSubjectClassEntity> findBySchoolClassId(Integer schoolClassId);
	
	public Optional<TeacherSubjectClassEntity> findByTeacherSubjectIdAndSchoolClassId(Integer teacherSubId, Integer schoolClassId);

	public Boolean existsBySchoolClassAndTeacherSubject(ClassEntity schoolClass, TeacherSubjectEntity teacherSubject);
	
	public List<TeacherSubjectClassEntity> findByStudentsCards(StudentsCardEntity studentsCard);
	
	public TeacherSubjectClassEntity findByTeacherSubjectAndSchoolClass(TeacherSubjectEntity teacherSubject, ClassEntity schoolClass);

	

}
