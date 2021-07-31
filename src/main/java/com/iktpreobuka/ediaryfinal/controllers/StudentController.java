package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;


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

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.StudentDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.StudentsCardDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.ParentRepository;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.services.ClassService;
import com.iktpreobuka.ediaryfinal.services.ParentService;
import com.iktpreobuka.ediaryfinal.services.StudentService;
import com.iktpreobuka.ediaryfinal.services.StudentsCardService;
import com.iktpreobuka.ediaryfinal.utils.ClassCustomValidator;
import com.iktpreobuka.ediaryfinal.utils.StudentCustomValidator;




@RestController
@RequestMapping(path = "/ediary/student")
public class StudentController {
	
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
		
		@Autowired
		private StudentService studentService;

		@Autowired
		private StudentRepository studentRepo;
		
		@Autowired
		StudentCustomValidator validator;
		
		@Autowired
		private RoleRepository roleRepo;
		
		@Autowired
		private ParentRepository parentRepo;
		
		@Autowired
		private ClassRepository classRepo;
		
		@Autowired
		private ParentService parentService;
		
		@Autowired
		private ClassService classService;

		@Autowired
		private StudentsCardService studentsCardService;

		@Autowired
		private StudentsCardRepository studentsCardRepo;
		
		
		
		@InitBinder("StudentDTO")
		protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(validator);
		}

	
		private String createErrorMessage(BindingResult result) {
			return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
		//@Secured({"ADMIN"})
		@GetMapping("/")
		public ResponseEntity<?> findAllStudents() {
			try {
				return new ResponseEntity<>((List<StudentEntity>) studentRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
			//@Secured({"ADMIN", "TEACHER"})
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id, HttpServletRequest request) {
				StudentEntity student = studentRepo.findById(id).get();
				if (studentRepo.existsById(id) && studentService.isActive(id)) {
					return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);
			}

			
			//@Secured("ADMIN")
			@PostMapping(value = "/")
			public ResponseEntity<?> create(@Valid @RequestBody StudentDTO newStudent, BindingResult result) {
				if (result.hasErrors()) {
					return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
				} else {
					validator.validate(newStudent, result);
				}
				StudentEntity student = new StudentEntity();
				student.setDeleted(false);
				student.setFirstName(newStudent.getFirstName());
				student.setLastName(newStudent.getLastName());
				student.setUsername(newStudent.getUsername());
				student.setPassword(newStudent.getPassword());
				student.setPasswordConfirmed(newStudent.getPasswordConfirmed());
				student.setRole(roleRepo.findById(4).get());
				studentRepo.save(student);
				return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
			}
			
		
			//@Secured("ADMIN")
			@PutMapping(value = "/{studentId}")
			public ResponseEntity<?> updateStudent(@PathVariable Integer studentId, @Valid @RequestBody StudentDTO newStudent, 
					BindingResult result) {
				if(studentRepo.existsById(studentId) && studentService.isActive(studentId)) {
					if (result.hasErrors()) {
						return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
					}
					ParentEntity parent = parentRepo.findById(newStudent.getParentId()).get();
					ClassEntity schoolClass = classRepo.findById(newStudent.getClassId()).get();
					StudentEntity student = studentRepo.findById(studentId).get();
					student.setDeleted(false);
					student.setFirstName(newStudent.getFirstName());
					student.setLastName(newStudent.getLastName());
					student.setUsername(newStudent.getUsername());
					student.setPassword(newStudent.getPassword());
					student.setPasswordConfirmed(newStudent.getPasswordConfirmed());
					student.setParent(parent);
					student.setSchoolClass(schoolClass);
					studentRepo.save(student);
	
					return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);
			}
			
		
			//@Secured("ADMIN")
			@DeleteMapping(value = "/{studentId}")
			public ResponseEntity<?> delete(@PathVariable Integer studentId) {
				if(studentRepo.existsById(studentId) && studentService.isActive(studentId)) {
					StudentEntity student = studentRepo.findById(studentId).get();
					student.setDeleted(true);
					studentRepo.save(student);
					return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);
			}

			
			//@Secured("ADMIN")
			@PostMapping(value = "/{studentId}/parent/{parentId}")
			public ResponseEntity<?> addParent(@PathVariable Integer studentId, @PathVariable Integer parentId) {
				if (studentRepo.existsById(studentId) && studentService.isActive(studentId)) {
					if (parentRepo.existsById(parentId) && parentService.isActive(parentId)) {
						StudentEntity student = studentRepo.findById(studentId).get();
						student.setParent(parentRepo.findById(parentId).get());
						studentRepo.save(student);
						return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
					}
					return new ResponseEntity<RESTError>(new RESTError("Parent not found."), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);
			}
		
		
						//@Secured("ADMIN")
						@PostMapping(value = "/{studentId}/schoolClass/{classId}")
						public ResponseEntity<?> addClass(@PathVariable Integer studentId, @PathVariable Integer classId) {
							if (studentRepo.existsById(studentId) && studentService.isActive(studentId)) {
								if (classRepo.existsById(classId) && classService.isActive(classId)) {
									StudentEntity student = studentRepo.findById(studentId).get();
									student.setSchoolClass(classRepo.findById(classId).get());
									studentRepo.save(student);
									return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
								}
								return new ResponseEntity<RESTError>(new RESTError("Class not found."), HttpStatus.NOT_FOUND);
							}
							return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);
						}
					

						
}