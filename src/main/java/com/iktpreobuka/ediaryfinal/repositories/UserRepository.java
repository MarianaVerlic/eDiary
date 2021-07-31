package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.UserEntity;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	

	public Optional<UserEntity> findByUsername (String username);
	
	public List<UserEntity> findByRole(String role);
	
}
