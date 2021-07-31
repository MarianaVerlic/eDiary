package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;



import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.MarkEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.MarkDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.repositories.AdminRepository;
import com.iktpreobuka.ediaryfinal.repositories.MarkRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;
import com.iktpreobuka.ediaryfinal.security.Views;
import com.iktpreobuka.ediaryfinal.services.MarkService;
import com.iktpreobuka.ediaryfinal.services.StudentService;
import com.iktpreobuka.ediaryfinal.services.StudentsCardService;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectService;

@RestController
@RequestMapping(path = "/ediary/mark")
public class MarkController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
		@Autowired
		private MarkService markService;

		@Autowired
		private MarkRepository markRepo;
		
		@Autowired
		private TeacherSubjectClassRepository teachSubClassRepo;

		@Autowired
		private StudentRepository studentRepo;

		@Autowired
		private StudentService studentService;

		@Autowired
		private TeacherSubjectRepository teacherCourseRepo;

		@Autowired
		private TeacherSubjectService teacherSubjectService;

		@Autowired
		private TeacherSubjectClassRepository teacherSubjectClassService;

		@Autowired
		private AdminRepository adminRepository;
		
		@Autowired
		private StudentsCardRepository studentsCardRepo;
		
		@Autowired
		private StudentsCardService studentsCardService;
		

			//@Secured("ADMIN")
			@GetMapping(value = "/")
			public ResponseEntity<?> getAll() {
				Iterable<MarkEntity>marks = markRepo.findAll();
				return new ResponseEntity<Iterable<MarkEntity>>(marks, HttpStatus.OK);
			}
			
			//@Secured({"ADMIN", "TEACHER", "PARENT", "STUDENT"})
			@GetMapping(value = "/{tscId}")
			public ResponseEntity<?> getAllByTeacherSubjectClass(@PathVariable Integer tscId) {
				Iterable<MarkEntity>marks = markRepo.findByStudentsCardTeacherSubjectClassId(tscId);
				return new ResponseEntity<Iterable<MarkEntity>>(marks, HttpStatus.OK);
			}
			

				
			
			//@Secured("ADMIN")
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id) {
				MarkEntity mark = markRepo.findById(id).get();
				if (markRepo.existsById(id)) {
					return new ResponseEntity<MarkEntity>(mark, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Mark not found."), HttpStatus.NOT_FOUND);
			}
			//@Secured({"ADMIN", "TEACHER"})
			@PostMapping(value = "/")
			public ResponseEntity<?> createNew(@Valid @RequestBody MarkDTO newMark) {
				MarkEntity mark = new MarkEntity();
				mark.setDeleted(false);
				mark.setMarkType(newMark.getMarkType());
				mark.setMarkValue(newMark.getMarkValue());
				markRepo.save(mark);
				return new ResponseEntity<MarkEntity>(mark, HttpStatus.OK);
			}
				
			//@Secured({"ADMIN", "TEACHER"})
			@PutMapping(value = "/{markId}")
			public ResponseEntity<?> updateMark(@PathVariable Integer markId, @Valid @RequestBody MarkDTO markDTO) {
					MarkEntity mark = markRepo.findById(markId).get();
					mark.setMarkType(markDTO.getMarkType());
					mark.setMarkValue(markDTO.getMarkValue());
				    markRepo.save(mark);
					return new ResponseEntity<MarkEntity>(mark, HttpStatus.OK);
				}
				
			


			
			//@Secured({"ADMIN"})
			@DeleteMapping(value = "/{id}")
			public ResponseEntity<?> deleteById(@PathVariable Integer id) {
				if (markRepo.existsById(id)) {
					MarkEntity me = markRepo.findById(id).get();
					me.setDeleted(true);
					markRepo.save(me);
					return new ResponseEntity<MarkEntity>(me, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Mark not found."), HttpStatus.NOT_FOUND);
			}
			
			
			//@Secured({"ADMIN","TEACHER"})
			@PostMapping(value = "/addMark")
			public ResponseEntity<?> addCardToMark(@RequestParam Integer studentsCardId, @RequestParam  Integer markId ) {
				if (studentsCardRepo.existsById(studentsCardId) && studentsCardService.isActive(studentsCardId)) {
					if (markRepo.existsById(markId)) {
						MarkEntity mark = markRepo.findById(markId).get();
						StudentsCardEntity studentsCard = studentsCardRepo.findById(studentsCardId).get();
						mark.setStudentsCard(studentsCard);
						markRepo.save(mark);
						try {
						markService.sendEmailToParent(studentsCard, mark);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return new ResponseEntity<MarkEntity>(mark, HttpStatus.OK);
					}
					return new ResponseEntity<RESTError>(new RESTError("Mark not found."), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student's card not found."), HttpStatus.NOT_FOUND);
			}
				
				
				
				
		}

				
			

			