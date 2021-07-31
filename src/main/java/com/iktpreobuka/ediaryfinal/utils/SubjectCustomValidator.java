package com.iktpreobuka.ediaryfinal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.iktpreobuka.ediaryfinal.entities.dto.SubjectDTO;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;

@Component
public class SubjectCustomValidator implements Validator{
		
		@Autowired
		private SubjectRepository subjectRepo;

		@Override
		public boolean supports(Class<?> clazz) {
			return SubjectDTO.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			SubjectDTO subject = (SubjectDTO) target;
			if(subjectRepo.existsByName(subject.getName()));  {
				errors.reject("400", "Subject name already in use.");
				
			}
			
		}
		
		

}
