package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;

@Service
public class TeacherServiceImpl implements TeacherService{
	
		@Autowired
		private TeacherRepository teacherRepo;
		
		@Autowired
		private TeacherSubjectRepository teachSubRepo;
		
		
		@PersistenceContext
		private EntityManager em;
		
		public Iterable<TeacherEntity> getAll() {
			return teacherRepo.findAll();
		}

		public Optional<TeacherEntity> findById(Integer id) {
			return teacherRepo.findById(id);
		}

		public TeacherEntity addNew(TeacherEntity newTeacher) {
			return teacherRepo.save(newTeacher);
		}
		
		public TeacherEntity delete(Integer id) {
			if (!teacherRepo.findById(id).isPresent()) {
				return null;
			}
			TeacherEntity te = teacherRepo.findById(id).get();
			teacherRepo.deleteById(id);
			return te;
		}
		
		
	
		@SuppressWarnings("static-access")
		public boolean ifExistsConectionTeacherSubject(TeacherEntity teacher, SubjectEntity subject) {
			Optional<TeacherSubjectEntity> ts = teachSubRepo.findByTeachersAndSubject(teacher, subject);
			if( ts.isPresent()) {
				return true;
			}else return false;
		}
		
		public boolean ifExists(Integer id) {
			if(teacherRepo.findById(id) != null) {
				return true;
			}else return false;
			
			
	}

		
		@Override
		public Boolean isActive(Integer parentId) {
			if(teacherRepo.existsById(parentId)) {
				TeacherEntity teacher = teacherRepo.findById(parentId).get();
				if(teacher.getDeleted().equals(true)) {
					return false;
				}
				return true;
			}
			return false;
		}
}
