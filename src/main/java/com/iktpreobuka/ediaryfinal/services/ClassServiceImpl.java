package com.iktpreobuka.ediaryfinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;

@Service
public class ClassServiceImpl implements ClassService{
	
		@Autowired
		private ClassRepository classRepo;
		
		
		@Autowired
		private StudentRepository studentRepo;
		
		@Autowired
		private SubjectRepository subjectRepo;
		
		@Autowired
		private TeacherRepository teacherRepo;
		
		@Autowired
		private TeacherSubjectRepository teacherSubjectRepo;
		
		
		@Autowired
		private TeacherSubjectClassRepository teachSubClassRepo;
		
		
		@PersistenceContext
		private EntityManager em;
		
		public Iterable<ClassEntity> getAll() {
			return classRepo.findAll();
		}

		public Optional<ClassEntity> findById(Integer id) {
			return classRepo.findById(id);
		}

		public ClassEntity addNew(ClassEntity newClass) {
			return classRepo.save(newClass);
		}

		
		public ClassEntity update(Integer id, ClassEntity newClass) {
			if (newClass == null || !classRepo.findById(id).isPresent()) {
				return null;
			}
			ClassEntity ce = classRepo.findById(id).get();
			ce.setSchoolYear(newClass.getSchoolYear());
			ce.setYear(newClass.getYear());
			ce.setClassNr(newClass.getClassNr());
			return classRepo.save(ce);
		}

		
		    public ClassEntity delete(Integer id) {
			if (!classRepo.findById(id).isPresent()) {
				return null;
			}
			ClassEntity ce = classRepo.findById(id).get();
			classRepo.deleteById(id);
			return ce;
		}
		    
		    public boolean ifExistsId(Integer id) {
				if (classRepo.findById(id) != null) {
					return true;
				} else
					return false;

			}
		    
		    
		   
		    public ClassEntity createNew(ClassDTO newClassDTO) {

				ClassEntity schoolclass = new ClassEntity();
				schoolclass.setYear(newClassDTO.getYear());
				schoolclass.setClassNr(newClassDTO.getClassNr());
				schoolclass.setSchoolYear(newClassDTO.getSchoolYear());
				return classRepo.save(schoolclass);
				
			}
		    @Override
			public Boolean isActive(Integer classId) {
				if(classRepo.existsById(classId)) {
					ClassEntity schoolClass = classRepo.findById(classId).get();
					if(schoolClass.getDeleted().equals(true)) {
						return false;
					}
					return true;
				}
				return false;
			}
		    
		    
		    
}

		