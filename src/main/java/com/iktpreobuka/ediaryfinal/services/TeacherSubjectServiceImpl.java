package com.iktpreobuka.ediaryfinal.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;

import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService{
	
	@Autowired
	TeacherSubjectRepository teachSubRepo;

	
	public List<TeacherSubjectEntity> findBySubject(Integer subjectId) {
		return teachSubRepo.findBySubjectId(subjectId);
	}
	
	public TeacherSubjectEntity delete(Integer id) {
		if(!teachSubRepo.findById(id).isPresent()) {
			return null;
		}
		TeacherSubjectEntity tse = teachSubRepo.findById(id).get();
		teachSubRepo.deleteById(id);
		return tse;
	}
	
	
	public Optional<TeacherSubjectEntity> findByTeacher(TeacherEntity teacher){
		return teachSubRepo.findByTeachers(teacher);
	}
	
	public TeacherSubjectEntity addNew(TeacherSubjectEntity newTS) {
		return teachSubRepo.save(newTS);
		
	
}

}
