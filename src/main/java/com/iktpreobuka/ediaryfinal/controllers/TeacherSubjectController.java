package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;

import java.util.stream.Collectors;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.controllers.util.Validation;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ParentDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.StudentsCardDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;
import com.iktpreobuka.ediaryfinal.services.StudentsCardService;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectService;


@RestController
@RequestMapping(path = "/ediary/teacherSubject")
public class TeacherSubjectController {
	
	@Autowired
	private TeacherSubjectService teacherSubjectService;

	@Autowired
	private TeacherSubjectRepository teachSubRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	
	//@Secured({"ADMIN"})
	@GetMapping("/all")
	public ResponseEntity<?> findAllTS() {
		try {
			return new ResponseEntity<>((List<TeacherSubjectEntity>) teachSubRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(
					new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	//@Secured("ADMIN")
	@PostMapping(value = "/")
	public ResponseEntity<?> createNew(@RequestParam String subjectName, @RequestParam String schoolYear) {
		TeacherSubjectEntity ts = new TeacherSubjectEntity();
		SubjectEntity subject = subjectRepo.findByName(subjectName);
		ts.setSubject(subject);
		ts.setSchoolYear(schoolYear);
		teachSubRepo.save(ts);
		return new ResponseEntity<TeacherSubjectEntity>(ts, HttpStatus.OK);
	}
	
	//@Secured("ADMIN")
		@GetMapping("/")
		public ResponseEntity<?> findBySubject(Integer subjectId) {
			try {
				return new ResponseEntity<>((List<TeacherSubjectEntity>) teachSubRepo.findBySubjectId(subjectId), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		
				
		
		
		
		
		//@Secured("ADMIN")
		@GetMapping(value = "/{id}")
		public ResponseEntity<?> getById(@PathVariable Integer id) {
			if (teachSubRepo.existsById(id)) {
				return new ResponseEntity<TeacherSubjectEntity>(teachSubRepo.findById(id).get(), HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError("Teacher-subject entity not found."), HttpStatus.NOT_FOUND);
		}


		
		
			
		//@Secured("ADMIN")
		@PutMapping(value = "/{tsId}")
		public ResponseEntity<?> update(@RequestParam String subjectName, @RequestParam String schoolYear, Integer tsId) {
			TeacherSubjectEntity tse = teachSubRepo.findById(tsId).get();
			SubjectEntity subject = subjectRepo.findByName(subjectName);
			tse.setSubject(subject);
			tse.setSchoolYear(schoolYear);
			teachSubRepo.save(tse);
			return new ResponseEntity<TeacherSubjectEntity>(tse, HttpStatus.OK);
			}
			
		
		//Obrisi po ID-u
		//@Secured("ADMIN")
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<?> deleteById(@PathVariable Integer id) {
			if (teachSubRepo.existsById(id)) {
				TeacherSubjectEntity tse = teachSubRepo.findById(id).get();
				tse.setDeleted(true);
				teachSubRepo.save(tse);
			return new ResponseEntity<TeacherSubjectEntity>(tse, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError("Teacher-subject connection  not found."), HttpStatus.NOT_FOUND);
		}			
			


		
	
	
	}



