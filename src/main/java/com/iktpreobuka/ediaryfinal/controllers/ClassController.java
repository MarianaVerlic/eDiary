package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;




import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.security.Views;
import com.iktpreobuka.ediaryfinal.services.ClassService;
import com.iktpreobuka.ediaryfinal.utils.ClassCustomValidator;


@RestController
@RequestMapping(path = "/ediary/class")
public class ClassController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClassService classService;

	@Autowired
	private ClassRepository classRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	ClassCustomValidator classValidator;
	
	@InitBinder("ClassDTO")
	protected void initBinder(final WebDataBinder binder) {
	binder.addValidators(classValidator);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	

	
	//@Secured("ADMIN")
	@GetMapping("/")
	public ResponseEntity<?> findAllClasses() {
		try {
			return new ResponseEntity<>((List<ClassEntity>) classRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(
					new RESTError("Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	//@Secured("ADMIN")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		if (classRepo.existsById(id) && classService.isActive(id)) {
			return new ResponseEntity<ClassEntity>(classRepo.findById(id).get(), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Class not found."), HttpStatus.NOT_FOUND);
	}


	//@Secured("ADMIN")
	@PostMapping(value = "/")
	public ResponseEntity<?> createNew(@Valid @RequestBody ClassDTO newClass, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			classValidator.validate(newClass, result);
		}
		ClassEntity schoolClass = new ClassEntity();
		schoolClass.setDeleted(false);
		schoolClass.setClassNr(newClass.getClassNr());
		schoolClass.setYear(newClass.getYear());
		schoolClass.setSchoolYear(newClass.getSchoolYear());
		classRepo.save(schoolClass);
		return new ResponseEntity<ClassEntity>(schoolClass, HttpStatus.OK);
	}
		
	//@Secured("ADMIN")
	@PutMapping(value = "/{classId}")
	public ResponseEntity<?> updateClass(@PathVariable Integer classId, @Valid @RequestBody ClassDTO classDTO,
			BindingResult result) {
		if (classRepo.existsById(classId) && classService.isActive(classId)) {
			if(result.hasErrors()) {
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			ClassEntity schoolClass = classRepo.findById(classId).get();
			schoolClass.setYear(classDTO.getYear());
			schoolClass.setClassNr(classDTO.getClassNr());
			schoolClass.setSchoolYear(classDTO.getSchoolYear());
			classRepo.save(schoolClass);
			return new ResponseEntity<ClassEntity>(schoolClass, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Class not found."), HttpStatus.NOT_FOUND);
	}


	
	//@Secured("ADMIN")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		if (classRepo.existsById(id) && classService.isActive(id)) {
			ClassEntity ce = classRepo.findById(id).get();
			ce.setDeleted(true);
			classRepo.save(ce);
			return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Class not found."), HttpStatus.NOT_FOUND);
	}
	
	//@Secured("ADMIN")
	@GetMapping(value = "/{classId}/students/")
	public ResponseEntity<?> studentsFromClass(@PathVariable Integer classId) {
		if(classRepo.existsById(classId) && classService.isActive(classId)) {
			ClassEntity schoolClass = classRepo.findById(classId).get();
			List<StudentEntity> students = ((List<StudentEntity>) studentRepo.findBySchoolClass(schoolClass))
					.stream().filter(student -> !student.getDeleted().equals(true)).collect(Collectors.toList());
			return new ResponseEntity<List<StudentEntity>>(students, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Class not found."), HttpStatus.NOT_FOUND);
	}

		
		
		
		
}

		