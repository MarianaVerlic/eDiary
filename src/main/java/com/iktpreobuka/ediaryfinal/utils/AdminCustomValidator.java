package com.iktpreobuka.ediaryfinal.utils;

import java.util.Set;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.ediaryfinal.entities.dto.AdminDTO;



@Component
public class AdminCustomValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AdminDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdminDTO admin = (AdminDTO) target;
		
		if(!admin.getPassword().equals(admin.getPasswordConfirmed())) {
			errors.reject("400", "Passwords don't match.");
		}
		
	}
	
	

}