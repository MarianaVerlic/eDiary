package com.iktpreobuka.ediaryfinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.ediaryfinal.entities.AdminEntity;
import com.iktpreobuka.ediaryfinal.entities.ClassEntity;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
	
	@Override
	public Optional<StudentEntity> findById(Integer id);

	public List<StudentEntity> findByParentId(Integer parentId);
	
	public Optional<StudentEntity> findBySchoolClassId(Integer classId);
	
	public StudentEntity findByUsername(String username);
	
	public Boolean existsByUsername(String username);

	public Boolean existsBySchoolClass(ClassEntity schoolClass);

	public List<StudentEntity> findBySchoolClass(ClassEntity schoolClass);
	
	public List<StudentEntity> findByParent(ParentEntity parent);
	
	



}
