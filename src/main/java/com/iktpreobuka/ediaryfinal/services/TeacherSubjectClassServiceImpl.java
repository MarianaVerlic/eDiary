package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
@Service
public class TeacherSubjectClassServiceImpl implements TeacherSubjectClassService{
	
	@Autowired
	TeacherSubjectClassRepository teachSubClassRepo;
	
	public TeacherSubjectClassEntity addTeacherSubjectToClass(TeacherSubjectClassEntity tse) {
		return teachSubClassRepo.save(tse);
	}

	
	@SuppressWarnings("static-access")
	public boolean ifExistsConectionTeacherSubjectClass(Integer tsId, Integer classId) {
		Optional<TeacherSubjectClassEntity> tsc = teachSubClassRepo.findByTeacherSubjectIdAndSchoolClassId(tsId, classId);
		if( tsc.isPresent()) {
			return true;
		}else return false;
	}
	
	public List<TeacherSubjectClassEntity> findByTeacherSubject(Integer tsId) {
		List<TeacherSubjectClassEntity> list = teachSubClassRepo.findByTeacherSubjectId(tsId);
		return list;
	}
	
	public TeacherSubjectClassEntity delete(Integer id) {
		if (!teachSubClassRepo.findById(id).isPresent()) {
			return null;
		}
		TeacherSubjectClassEntity tsce = teachSubClassRepo.findById(id).get();
		teachSubClassRepo.deleteById(id);
		return tsce;
	}
	
	public Optional<TeacherSubjectClassEntity> findByTeacherSubjectClass(Integer tsId, Integer classId) {
		return teachSubClassRepo.findByTeacherSubjectIdAndSchoolClassId(tsId, classId);
	}
	
	
	
	
	
}
