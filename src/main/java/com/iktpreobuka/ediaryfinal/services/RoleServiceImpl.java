package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	public Iterable<RoleEntity> getAll(){
		return (List<RoleEntity>)roleRepo.findAll();
	}
	
	public RoleEntity getById(Integer id) {
		return roleRepo.findById(id).get();
	}
	
	public RoleEntity save(RoleEntity role) {
		return roleRepo.save(role);
	}
	
	public RoleEntity delete(Integer id) {
		RoleEntity role= roleRepo.findById(id).get();
		if(!(role.getUsers().isEmpty())) {
			return null;
		}
		roleRepo.delete(role);
		return role;
	}
	
	public RoleEntity update(Integer id, RoleEntity newRole) {
		RoleEntity role= roleRepo.findById(id).get();
		role.setRoleName(newRole.getRoleName());
		return roleRepo.save(role);
	}
	
	
	public boolean ifExists(String name) {
		if(roleRepo.findByRoleName(name) != null) {
			return true;
		}else return false;
		
		
	}
	public Optional<RoleEntity> findByRoleName(String name) {
		return roleRepo.findByRoleName(name);
	}
	

	public RoleEntity create(RoleEntity newRole) {
			RoleEntity role = new RoleEntity();
			role.setRoleName(newRole.getRoleName());
			roleRepo.save(role);
			return role;
		}
		
	}
	



