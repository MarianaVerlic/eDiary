package com.iktpreobuka.ediaryfinal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.ediaryfinal.entities.dto.ClassDTO;
import com.iktpreobuka.ediaryfinal.repositories.ClassRepository;

@Component
public class ClassCustomValidator implements Validator{
		
		@Autowired
		private ClassRepository classRepo;

		@Override
		public boolean supports(Class<?> clazz) {
			return ClassDTO.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			ClassDTO classDTO = (ClassDTO) target;
			
			if(classRepo.existsByClassNrAndYear(classDTO.getClassNr(), classDTO.getYear())) {
				errors.reject("400", "Class year-number combination is already in use.");
			}
			
		}
		
		

	}


