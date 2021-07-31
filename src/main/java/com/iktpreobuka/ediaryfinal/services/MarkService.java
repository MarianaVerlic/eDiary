package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.iktpreobuka.ediaryfinal.entities.MarkEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;

public interface MarkService {

	 public MarkEntity deleteMark(Integer id);
	 
	 public MarkEntity updateMark(Integer id, MarkEntity newMark);
	 
	 public MarkEntity addNewMark(MarkEntity newMark);
	 
	 public Optional<MarkEntity> findById(Integer id);
	 
	 public Iterable<MarkEntity> getAllMarks();
	 
	 public List<MarkEntity> findByStudentsCard(Integer studentsCardId);
	 
	 public List<MarkEntity> findByTeacherSubjectClass(Integer teacherSubjectClassId);
	 
	 public void sendEmailToParent(StudentsCardEntity sce, MarkEntity mark) throws MessagingException;
	 
}