package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.MarkEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.StudentsCardDTO;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.MarkRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
import com.iktpreobuka.ediaryfinal.services.ClassService;
import com.iktpreobuka.ediaryfinal.services.StudentService;
import com.iktpreobuka.ediaryfinal.services.StudentsCardService;
import com.iktpreobuka.ediaryfinal.services.TeacherSubjectClassService;

@RestController
@RequestMapping(path = "/ediary/studentsCard")
public class StudentsCardController {
	
	@Autowired
	private StudentsCardService studentsCardService;

	@Autowired
	private StudentsCardRepository studentsCardRepo;
	
	@Autowired
	private MarkRepository markRepo;
	
	@Autowired
	private TeacherSubjectClassService teachSubClassService;
	
	@Autowired
	private TeacherSubjectClassRepository teachSubClassRepo;
	
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private ClassRepository classRepo;

	
	//@Secured("ADMIN")
	@GetMapping("/")
	public ResponseEntity<?> findAllCardsByStudent(Integer studentId) {
		try {
			return new ResponseEntity<>((List<StudentsCardEntity>) studentsCardRepo.findByStudentId(studentId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(
					new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	//@Secured("ADMIN")
	@PostMapping(value = "/")
	public ResponseEntity<?> createNew(@Valid @RequestBody StudentsCardDTO newCard) {
		StudentsCardEntity card = new StudentsCardEntity();
		card.setDeleted(false);
		card.setAbsences(newCard.getAbsences());
		card.setNotes(newCard.getNotes());
		studentsCardRepo.save(card);
		return new ResponseEntity<StudentsCardEntity>(card, HttpStatus.OK);
	}
	//@Secured("ADMIN")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		if (studentsCardRepo.existsById(id) && studentsCardService.isActive(id)) {
			return new ResponseEntity<StudentsCardEntity>(studentsCardRepo.findById(id).get(), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Card not found."), HttpStatus.NOT_FOUND);
	}


	
	
		
	//@Secured({"ADMIN", "TEACHER"})
	@PutMapping(value = "/{studentsCardId}")
	public ResponseEntity<?> update(@PathVariable Integer studentsCardId, @Valid @RequestBody StudentsCardDTO cardDTO) {
			StudentsCardEntity scard = studentsCardRepo.findById(studentsCardId).get();
			scard.setAbsences(cardDTO.getAbsences());
			scard.setNotes(cardDTO.getNotes());
			studentsCardRepo.save(scard);
			return new ResponseEntity<StudentsCardEntity>(scard, HttpStatus.OK);
		}
		
	


	
	//@Secured("ADMIN")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		if (studentsCardRepo.existsById(id) && studentsCardService.isActive(id)) {
			StudentsCardEntity sce = studentsCardRepo.findById(id).get();
			sce.setDeleted(true);
			studentsCardRepo.save(sce);
		return new ResponseEntity<StudentsCardEntity>(sce, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError("Card not found."), HttpStatus.NOT_FOUND);
	}			
		
	// Dodaj predmet kod nastavnika u razredu kartici ucenika
		//@Secured("ADMIN")
		@PostMapping(value = "/addTeacherSubjectClass")
		public ResponseEntity<?> addTeacherSubjectClassToCard(@RequestParam Integer id, @RequestParam  Integer tscId ) {
			if (studentsCardRepo.existsById(id) && studentsCardService.isActive(id)) {
				if (teachSubClassRepo.existsById(tscId)) {
					StudentsCardEntity studentsCard = studentsCardRepo.findById(id).get();
					TeacherSubjectClassEntity tsce = teachSubClassRepo.findById(tscId).get();
					studentsCard.setTeacherSubjectClass(tsce);
					studentsCardRepo.save(studentsCard);
					return new ResponseEntity<StudentsCardEntity>(studentsCard, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher-subject-class connection not found."), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<RESTError>(new RESTError("Student's card not found."), HttpStatus.NOT_FOUND);
		}

		
		
	
		 //Dodaj studenta
			//@Secured("ADMIN")
			@PostMapping(value = "/addStudent")
			public ResponseEntity<?> addStudentToCard(@RequestParam Integer studentsCardId, @RequestParam Integer studentId) {
				if (studentRepo.existsById(studentId) && studentService.isActive(studentId)) {
						StudentsCardEntity studentsCard = studentsCardRepo.findById(studentsCardId).get();
						StudentEntity student = studentRepo.findById(studentId).get();
						List<StudentEntity> students = studentsCard.getTeacherSubjectClass().getSchoolClass().getStudents();
						for (StudentEntity studentClass : students) 
							if (student.getId().equals(studentClass.getId())) {
								studentsCard.setStudent(student);
								studentsCardRepo.save(studentsCard);
								return new ResponseEntity<StudentsCardEntity>(studentsCard, HttpStatus.OK);
							}
							return new ResponseEntity<RESTError>(new RESTError("Student is not from that class"), HttpStatus.NOT_FOUND);	
							}
				return new ResponseEntity<RESTError>(new RESTError("Student not found."), HttpStatus.NOT_FOUND);	
			}
						
						
			//@Secured("ADMIN")
			@GetMapping(value = "/{studentsCardId}/marks/")
			public ResponseEntity<?> marksFromCard(@PathVariable Integer studentsCardId) {
				if(studentsCardRepo.existsById(studentsCardId) && studentsCardService.isActive(studentsCardId)) {
					StudentsCardEntity studentsCard = studentsCardRepo.findById(studentsCardId).get();
					List<MarkEntity> marks = ((List<MarkEntity>) markRepo.findByStudentsCardId(studentsCardId))
							.stream().filter(mark -> !mark.getDeleted().equals(true)).collect(Collectors.toList());
					return new ResponseEntity<List<MarkEntity>>(marks, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Student's card not found."), HttpStatus.NOT_FOUND);
			}

}
						
			
		

	


