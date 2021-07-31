package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectClassService;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectService;

@RestController
@RequestMapping(path = "/ediary/teacherSubjectClass")
public class TeacherSubjectClassController {

	@Autowired
	private TeacherSubjectClassService teacherSubjectClassService;

	@Autowired
	private TeacherSubjectClassRepository teachSubClassRepo;
	
	@Autowired
	private ClassRepository classRepo;
	
	@Autowired
	private TeacherSubjectRepository teachSubRepo;
	
	
	

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	
	}
	    //@Secured("ADMIN")
		@PostMapping(value = "/teacherSubject/{tsId}/class/{classId}")
		public ResponseEntity<?> createNew(@PathVariable Integer tsId, @PathVariable Integer classId) {
			TeacherSubjectClassEntity tsc=new TeacherSubjectClassEntity();
			ClassEntity schoolClass=classRepo.findById(classId).get();
			TeacherSubjectEntity teacherSubject=teachSubRepo.findById(tsId).get();
			tsc.setSchoolClass(schoolClass);
			tsc.setTeacherSubject(teacherSubject);
			teachSubClassRepo.save(tsc);
			return new ResponseEntity<TeacherSubjectClassEntity>(tsc, HttpStatus.OK);
		}
	
		//@Secured({"ADMIN"})
				@GetMapping("/all")
				public ResponseEntity<?> findAllTSC() {
					try {
						return new ResponseEntity<>((List<TeacherSubjectClassEntity>) teachSubClassRepo.findAll(), HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<RESTError>(
								new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
				}
		
		
		
		
		
		//@Secured("ADMIN")
			@GetMapping("/")
			public ResponseEntity<?> findByTeacherSubject(Integer tsId) {
				try {
					return new ResponseEntity<>((List<TeacherSubjectClassEntity>) teachSubClassRepo.findByTeacherSubjectId(tsId), HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<RESTError>(
							new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
			
			
					
			
			
			
			
			//@Secured("ADMIN")
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id) {
				if (teachSubRepo.existsById(id)) {
					return new ResponseEntity<TeacherSubjectClassEntity>(teachSubClassRepo.findById(id).get(), HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher-subject-class entity not found."), HttpStatus.NOT_FOUND);
			}


			
			
				
			//@Secured("ADMIN")
			@PutMapping(value = "/{tsId}")
			public ResponseEntity<?> update(@PathVariable Integer tsId, @PathVariable Integer classId) {
				TeacherSubjectClassEntity tsce = teachSubClassRepo.findById(tsId).get();
				ClassEntity schoolClass = classRepo.findById(classId).get();
				TeacherSubjectEntity ts = teachSubRepo.findById(tsId).get();
				tsce.setSchoolClass(schoolClass);
				tsce.setTeacherSubject(ts);
				teachSubClassRepo.save(tsce);
				return new ResponseEntity<TeacherSubjectClassEntity>(tsce, HttpStatus.OK);
				}
				
			
			// Obrisi po ID-u
			//@Secured("ADMIN")
			@DeleteMapping(value = "/{id}")
			public ResponseEntity<?> deleteById(@PathVariable Integer id) {
				if (teachSubClassRepo.existsById(id)) {
					TeacherSubjectClassEntity tsce = teachSubClassRepo.findById(id).get();
					tsce.setDeleted(true);
					teachSubClassRepo.save(tsce);
				return new ResponseEntity<TeacherSubjectClassEntity>(tsce, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher-subject-class connection  not found."), HttpStatus.NOT_FOUND);
			}			
				


			
		
}
