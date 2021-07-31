package com.iktpreobuka.ediaryfinal.services;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.controllers.util.Validation;
import com.iktpreobuka.ediaryfinal.entities.AdminEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.UserEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.repositories.AdminRepository;
import com.iktpreobuka.ediaryfinal.repositories.ParentRepository;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.UserRepository;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private ParentRepository parentRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	
	
	@PersistenceContext
 	private EntityManager em;
 	
	public Iterable<AdminEntity> getAll() {
		return adminRepo.findAll();
	}

	public Optional<AdminEntity> findById(Integer id) {
		return adminRepo.findById(id);
	}

	public AdminEntity add(AdminEntity newAdmin) {
		return adminRepo.save(newAdmin);
	}
	
	
	public AdminEntity update(Integer id, AdminEntity newAdmin) {
		if (newAdmin == null || !adminRepo.findById(id).isPresent()) {
			return null;
		}
		AdminEntity ae = adminRepo.findById(id).get();
		ae.setFirstName(newAdmin.getFirstName());
		ae.setLastName(newAdmin.getLastName());
		ae.setUsername(newAdmin.getUsername());
		ae.setDeleted(newAdmin.getDeleted());
		return adminRepo.save(ae);
	}
	
	public AdminEntity delete(Integer id) {
		if (!adminRepo.findById(id).isPresent()) {
			return null;
		}
		AdminEntity ae = adminRepo.findById(id).get();
		adminRepo.deleteById(id);
		return ae;
	}
	
	 
	 
	 public boolean ifExists(Integer id) {
			if(adminRepo.findById(id) != null) {
				return true;
			}else return false;
			
			
		}


	

	@SuppressWarnings("unchecked")
 	@Override
 	public List<String> getAllUsernames() {
 		String sql = "SELECT * FROM usernames";
 		Query query = em.createNativeQuery(sql);
 		List<String> result = new ArrayList<>();
 		result = query.getResultList();
 		return result;
 	}
 	
	
	 	
	 	@Override
	 	public Integer findIdByUsername(String username) {
	 		AdminEntity admin = adminRepo.findByUsername(username);
	 		ParentEntity parent = parentRepo.findByUsername(username);
	 		StudentEntity student = studentRepo.findByUsername(username);
	 		TeacherEntity teacher = teacherRepo.findByUsername(username);
	 		
	 		if(admin != null) {
	 			return admin.getId();
	 		}
	 		if(parent != null) {
	 			return parent.getId();
	 		}
	 		if(student != null) {
	 			return student.getId();
	 		}
	 		if(teacher != null) {
	 			return teacher.getId();
	 		}
	 		return null;
	 	}

	 
	@Override
	public Boolean isActive(Integer adminId) {
	if(adminRepo.existsById(adminId)) {
		AdminEntity admin = adminRepo.findById(adminId).get();
		if(admin.getDeleted().equals(true)) {
			return false;
		}
		return true;
	}
	return false;
}


}
