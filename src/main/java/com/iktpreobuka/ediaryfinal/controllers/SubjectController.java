package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.controllers.util.Validation;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.services.SubjectService;
import com.iktpreobuka.ediaryfinal.utils.SubjectCustomValidator;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;

@RestController
@RequestMapping(path = "/ediary/subject")
public class SubjectController {


		@Autowired
		private SubjectService subjectService;

		@Autowired
		private SubjectRepository subjectRepo;
		
		@Autowired
		SubjectCustomValidator validator;

		@InitBinder
		protected void initBinder(final WebDataBinder binder) {
			binder.addValidators(validator);
		}
		
		
		//@Secured({"ADMIN", "TEACHER","PARENT", "STUDENT" })
		@GetMapping("/")
		public ResponseEntity<?> findAllSubjects() {
			try {
				return new ResponseEntity<>((List<SubjectEntity>) subjectRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		//@Secured("ADMIN")
		@GetMapping("/{id}")
		public ResponseEntity<?> findById(@PathVariable Integer id) {
			Optional<SubjectEntity> subject = subjectRepo.findById(id);
			if (subject.isPresent()) {
				return new ResponseEntity<>(subject, HttpStatus.OK);
			}

			return new ResponseEntity<>(new RESTError("Subject not found"),HttpStatus.NOT_FOUND);
		}
		
		//@Secured("ADMIN")
		@PostMapping("/")
		public ResponseEntity<?> addSubject(@Valid @RequestBody SubjectDTO subjectDTO, BindingResult result) {

			if (result.hasErrors()) {
				return new ResponseEntity<>(Validation.createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			SubjectEntity subject = subjectService.createSubject(subjectDTO);
			return new ResponseEntity<>(subject, HttpStatus.CREATED);
		}
		//@Secured("ADMIN")
		@PutMapping("/{id}")
		public ResponseEntity<?> changeSubject(@PathVariable Integer id, @Valid @RequestBody SubjectEntity subject,
				BindingResult result) {

			if (result.hasErrors()) {
				return new ResponseEntity<>(Validation.createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(subjectService.changeSubject(id, subject), HttpStatus.OK);

		}
		//@Secured("ADMIN")
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteById(@PathVariable Integer id) {
			Optional<SubjectEntity> subject = subjectRepo.findById(id);

			if (subject.isPresent()) {
				subjectRepo.delete(subject.get());
				return new ResponseEntity<>(subject, HttpStatus.OK);
			}
			return new ResponseEntity<>(new RESTError("Subject not found"),HttpStatus.NOT_FOUND);
		}
		//@Secured("ADMIN")
		@GetMapping("/findByTeacher/{teacherId}")
		public ResponseEntity<?> findSubjectByTeacher(@PathVariable Integer teacherId) {
			try {
				return new ResponseEntity<>(subjectRepo.findByTeacherSubjectTeachersId(teacherId), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError("Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
}