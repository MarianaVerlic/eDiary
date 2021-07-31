package com.iktpreobuka.ediaryfinal.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.ediaryfinal.entities.dto.StudentDTO;

@Component
public class StudentCustomValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return StudentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentDTO student = (StudentDTO) target;
		
		if(!student.getPassword().equals(student.getPasswordConfirmed())) {
			errors.reject("400", "Passwords don't match.");
		}
		
	}

}
