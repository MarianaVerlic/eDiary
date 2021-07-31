package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.controllers.util.Validation;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{
	

		@Autowired
		SubjectRepository subjectRepo;
		
		
		@Autowired
		TeacherSubjectRepository teachSubRepo;

		public Iterable<SubjectEntity> findAllSubject() {
			return subjectRepo.findAll();
		}


		@Override
		public SubjectEntity createSubject(SubjectDTO subjectDTO) {
			SubjectEntity subject = new SubjectEntity(subjectDTO.getName(), subjectDTO.getClassLoad());
			subjectRepo.save(subject);
			return subject;
		}

		public SubjectEntity changeSubject(Integer id, SubjectEntity subject) {
			SubjectEntity subjectDb = Validation.validateEntity(id, subjectRepo);
			subjectDb.setName(Validation.setIfNotNull(subjectDb.getName(), subject.getName()));
			subjectDb.setClassLoad(Validation.setIfNotNull(subjectDb.getClassLoad(), subject.getClassLoad()));
			subjectRepo.save(subjectDb);

			return subjectDb;

		}
		
		public Optional<SubjectEntity> findById(Integer id) {
			return subjectRepo.findById(id);
		}

		public SubjectEntity addNewSubject(SubjectEntity newSubject) {
			return subjectRepo.save(newSubject);
		}

		

		public SubjectEntity deleteSubject(Integer id) {
			if (!subjectRepo.findById(id).isPresent()) {
				return null;
			}
			SubjectEntity sub = subjectRepo.findById(id).get();
			subjectRepo.deleteById(id);
			return sub;
		}

		public boolean ifExists(Integer id) {
			if (subjectRepo.findById(id) != null) {
				return true;
			} else
				return false;

		}

		public boolean ifExistsName(String name) {
			if (subjectRepo.findByName(name) != null) {
				return true;
			} else
				return false;

		}

		
	}
	