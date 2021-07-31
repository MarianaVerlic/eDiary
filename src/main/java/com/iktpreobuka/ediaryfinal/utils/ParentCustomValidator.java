package com.iktpreobuka.ediaryfinal.utils;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.ediaryfinal.entities.dto.ParentDTO;

@Component
public class ParentCustomValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ParentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ParentDTO parent = (ParentDTO) target;
		
		if(!parent.getPassword().equals(parent.getPasswordConfirmed())) {
			errors.reject("400", "Passwords don't match.");
		}
		
	}
}

