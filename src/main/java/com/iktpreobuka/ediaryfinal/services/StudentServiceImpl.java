package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@PersistenceContext
	private EntityManager em;
	

	public Iterable<StudentEntity> getAll() {
		return studentRepo.findAll();
	}

	public Optional<StudentEntity> findById(Integer id) {
		return studentRepo.findById(id);
	}

	public StudentEntity add(StudentEntity newStudent) {
		return studentRepo.save(newStudent);
	}
	
	public StudentEntity update(Integer id, StudentEntity newStudent) {
		if (newStudent == null || !studentRepo.findById(id).isPresent()) {
			return null;
		}
		StudentEntity se = studentRepo.findById(id).get();
		se.setFirstName(newStudent.getFirstName());
		se.setLastName(newStudent.getLastName());
		se.setUsername(newStudent.getUsername());
		se.setParent(newStudent.getParent());
		se.setSchoolClass(newStudent.getSchoolClass());
		return studentRepo.save(se);
		
	}

	public StudentEntity delete(Integer id) {
		if (!studentRepo.findById(id).isPresent()) {
			return null;
		}
		StudentEntity se = studentRepo.findById(id).get();
		studentRepo.deleteById(id);
		return se;
	}
	
	
	public Optional<StudentEntity> findStudentsByClass(Integer classId) {
		return studentRepo.findBySchoolClassId(classId);
		
	}
	
	
	public List <StudentEntity> findStudentsByParent(Integer parentId){
		return studentRepo.findByParentId(parentId);
	}
	
	public boolean ifExists(Integer id) {
		if(studentRepo.findById(id) != null) {
			return true;
		}else return false;
		
		
	}
	
	@Override
	public Boolean isActive(Integer studentId) {
		if(studentRepo.existsById(studentId)) {
			StudentEntity student = studentRepo.findById(studentId).get();
			if(student.getDeleted().equals(true)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
}
