package com.iktpreobuka.ediaryfinal.controllers;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.RoleEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.RoleDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.UserRepository;
import com.iktpreobuka.ediaryfinal.services.RoleService;
import com.iktpreobuka.ediaryfinal.services.SubjectService;
import com.iktpreobuka.ediaryfinal.services.TeacherService;



@RestController
@RequestMapping(path = "/ediary/role")
public class RoleController {
	
		@Autowired
		private RoleRepository roleRepo;
		
		@Autowired
		private RoleService roleService;
		
		
		

		private String createErrorMessage(BindingResult result) {
			return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
		
		

		@GetMapping("/")
		public ResponseEntity<?> findAllRoles() {
			try {
				return new ResponseEntity<>((List<RoleEntity>) roleRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
			
			
		
			@PostMapping("/")
			public ResponseEntity<?> createRole(@Valid @RequestBody RoleEntity newRole, BindingResult result) {
				try {
					if (newRole != null) {
						RoleEntity role = roleService.create(newRole);
						return new ResponseEntity<RoleEntity>(role, HttpStatus.OK);
					}
					return new ResponseEntity<RESTError>(new RESTError("Failed to create a role."), HttpStatus.NO_CONTENT);
				} catch (Exception e) {
					return new ResponseEntity<RESTError>(
							new RESTError("Error ocured. Error message " + e.getMessage()
									+ e.getStackTrace() + ".\n Stack trace:	"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

}				
			

		
		
		
		
