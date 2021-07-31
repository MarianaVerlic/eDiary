package com.iktpreobuka.ediaryfinal.controllers;


import java.util.ArrayList;




import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.UserEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.RoleDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.repositories.AdminRepository;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.UserRepository;
import com.iktpreobuka.ediaryfinal.security.Views;
import com.iktpreobuka.ediaryfinal.services.ClassService;
import com.iktpreobuka.ediaryfinal.services.SubjectService;
import com.iktpreobuka.ediaryfinal.services.TeacherService;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectService;
import com.iktpreobuka.ediaryfinal.utils.TeacherCustomValidator;


@RestController
@RequestMapping(path = "/ediary/teacher")
public class TeacherController {
	
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	TeacherCustomValidator validator;
	
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	
	@Autowired
	private TeacherSubjectRepository teachSubRepo;
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private TeacherCustomValidator teacherValidator;
	
	@InitBinder("TeacherDTO")
	protected void initBinder(final WebDataBinder binder) {
	binder.addValidators(validator);
	}

	

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	//@Secured({"ADMIN","TEACHER"})
	@GetMapping("/")
		public ResponseEntity<?> findAllTeachers() {
			try {
				return new ResponseEntity<>((List<TeacherEntity>) teacherRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	
	

			
			//@Secured({"ADMIN", "TEACHER"})
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id, HttpServletRequest request) {
				TeacherEntity teacher = teacherRepo.findById(id).get();
				if (teacherRepo.existsById(id) && teacherService.isActive(id)) {
					return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);
			}

			
			//@Secured("ADMIN")
			@PostMapping(value = "/")
			public ResponseEntity<?> create(@Valid @RequestBody TeacherDTO newTeacher, BindingResult result) {
				if (result.hasErrors()) {
					return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
				} else {
					teacherValidator.validate(newTeacher, result);
				}
				TeacherEntity teacher = new TeacherEntity();
				teacher.setDeleted(false);
				teacher.setFirstName(newTeacher.getFirstname());
				teacher.setLastName(newTeacher.getLastname());
				teacher.setUsername(newTeacher.getUsername());
				teacher.setPassword(newTeacher.getPassword());
				teacher.setPasswordConfirmed(newTeacher.getPasswordConfirmed());
				teacher.setEmail(newTeacher.getEmail());
				teacher.setRole(roleRepo.findById(2).get());
				teacherRepo.save(teacher);
				return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
			}
			
			
			//@Secured("ADMIN")
			@PutMapping(value = "/{teacherId}")
			public ResponseEntity<?> updateTeacher(@PathVariable Integer teacherId, @Valid @RequestBody TeacherDTO newTeacher, 
					BindingResult result) {
				if(teacherRepo.existsById(teacherId) && teacherService.isActive(teacherId)) {
					if (result.hasErrors()) {
						return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
					}
					TeacherEntity teacher = teacherRepo.findById(teacherId).get();
					
					teacher.setFirstName(newTeacher.getFirstname());
					teacher.setLastName(newTeacher.getLastname());
					teacher.setEmail(newTeacher.getEmail());
					teacher.setUsername(newTeacher.getUsername());
					teacher.setPassword(newTeacher.getPassword());
					teacher.setPasswordConfirmed(newTeacher.getPasswordConfirmed());
					teacher.setEmail(newTeacher.getEmail());
					teacherRepo.save(teacher);
	
					return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);
			}
			
			
			//@Secured("ADMIN")
			@DeleteMapping(value = "/{teacherId}")
			public ResponseEntity<?> deleteTeacher(@PathVariable Integer teacherId) {
				if(teacherRepo.existsById(teacherId) && teacherService.isActive(teacherId)) {
					TeacherEntity teacher = teacherRepo.findById(teacherId).get();
					teacher.setDeleted(true);
					teacherRepo.save(teacher);
					return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);
			}
			
			
			
			//@Secured("ADMIN")
			@PostMapping(value = "/addTeacherSubject")
			public ResponseEntity<?> addTeacherSubject(@RequestParam Integer teacherId, @RequestParam Integer tsId) {
				if (teacherRepo.existsById(teacherId) && teacherService.isActive(teacherId)) {
						TeacherEntity teacher = teacherRepo.findById(teacherId).get();
						TeacherSubjectEntity tse = teachSubRepo.findById(tsId).get();
						teacher.setTeacherSubject(tse);
						teacherRepo.save(teacher);
								return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
							}
							return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);	
							}
					
			}
			

