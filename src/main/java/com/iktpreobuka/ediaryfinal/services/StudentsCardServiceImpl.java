package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;

@Service
public class StudentsCardServiceImpl implements StudentsCardService{
	
	@Autowired
	private StudentsCardRepository studentsCardRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private TeacherSubjectClassRepository teachSubClassRepo;
	
	@PersistenceContext
	private EntityManager em;
	

	public Iterable<StudentsCardEntity> getAll() {
		return studentsCardRepo.findAll();
	}

	public Optional<StudentsCardEntity> findById(Integer id) {
		return studentsCardRepo.findById(id);
	}

	public StudentsCardEntity add(StudentsCardEntity newStudentsCard) {
		return studentsCardRepo.save(newStudentsCard);
	}
	
	
	
	
	
	
	
	
	public StudentsCardEntity update(Integer id, StudentsCardEntity newSce) {
		if (newSce == null || !studentsCardRepo.findById(id).isPresent()) {
			return null;
		}
		StudentsCardEntity sce = studentsCardRepo.findById(id).get();
		sce.setTeacherSubjectClass(newSce.getTeacherSubjectClass());
		sce.setAbsences(newSce.getAbsences());
		sce.setNotes(newSce.getNotes());
		return studentsCardRepo.save(sce);
		
	}

	public StudentsCardEntity delete(Integer id) {
		if (!studentsCardRepo.findById(id).isPresent()) {
			return null;
		}
		StudentsCardEntity sce = studentsCardRepo.findById(id).get();
		studentsCardRepo.deleteById(id);
		return sce;
	}
	
	
	public List<StudentsCardEntity> findByStudent(Integer studentId){
		return studentsCardRepo.findByStudentId(studentId);
	
	}
	
	
	public boolean ifExists(Integer id) {
		if(studentsCardRepo.findById(id) != null) {
			return true;
		}else return false;
		
		
	}
	

@Override
public Boolean isActive(Integer scId) {
	if(studentsCardRepo.existsById(scId)) {
		StudentsCardEntity sc = studentsCardRepo.findById(scId).get();
		if(sc.getDeleted().equals(true)) {
			return false;
		}
		return true;
	}
	return false;
}
	
	
}



