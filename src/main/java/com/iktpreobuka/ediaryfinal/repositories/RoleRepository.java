package com.iktpreobuka.ediaryfinal.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
	

	public Optional<RoleEntity> findById(Integer id);
	
	public Optional <RoleEntity> findByRoleName(String roleName);
	
}
