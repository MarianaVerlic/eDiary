package com.iktpreobuka.ediaryfinal.services;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.repositories.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService{
	
		@Autowired
		private ParentRepository parentRepo;

		@PersistenceContext
		private EntityManager em;

		public Iterable<ParentEntity> getAllParents() {
			return parentRepo.findAll();
		}

		public Optional<ParentEntity> findById(Integer id) {
			return parentRepo.findById(id);
		}

		public ParentEntity addNewParent(ParentEntity newParent) {
			return parentRepo.save(newParent);
		}

		public ParentEntity updateParent(Integer id, ParentEntity newParent) {
			if (newParent == null || !parentRepo.findById(id).isPresent()) {
				return null;
			}
			ParentEntity pe = parentRepo.findById(id).get();
			pe.setFirstName(newParent.getFirstName());
			pe.setLastName(newParent.getLastName());
			pe.setUsername(newParent.getUsername());
			pe.setEmail(newParent.getEmail());
			pe.setChildren(newParent.getChildren());
			return parentRepo.save(pe);
		}

		
		public ParentEntity deleteParent(Integer id) {
			if (!parentRepo.findById(id).isPresent()) {
				return null;
			}
			ParentEntity pe = parentRepo.findById(id).get();
			parentRepo.deleteById(id);
			return pe;
		}

		public boolean ifExists(Integer id) {
			if (parentRepo.findById(id) != null) {
				return true;
			} else
				return false;

		}
		@Override
		public Boolean isActive(Integer parentId) {
			if(parentRepo.existsById(parentId)) {
				ParentEntity parent = parentRepo.findById(parentId).get();
				if(parent.getDeleted().equals(true)) {
					return false;
				}
				return true;
			}
			return false;
		}
		
		
}
