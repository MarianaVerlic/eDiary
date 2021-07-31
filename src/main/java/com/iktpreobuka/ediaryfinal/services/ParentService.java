package com.iktpreobuka.ediaryfinal.services;

import java.util.Optional;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;

public interface ParentService {
	
	public Iterable<ParentEntity> getAllParents();
	
	public Optional<ParentEntity> findById(Integer id);
	
	public ParentEntity addNewParent(ParentEntity newParent);
	
	public ParentEntity updateParent(Integer id, ParentEntity newParent);
	
	public ParentEntity deleteParent(Integer id);
	 
	public boolean ifExists(Integer id);

	public Boolean isActive(Integer parentId);
	
	

}
