package com.iktpreobuka.ediaryfinal.controllers;


import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.entities.ParentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.ParentDTO;
import com.iktpreobuka.ediaryfinal.repositories.AdminRepository;
import com.iktpreobuka.ediaryfinal.repositories.ParentRepository;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.services.ParentService;
import com.iktpreobuka.ediaryfinal.utils.ParentCustomValidator;

@RestController
@RequestMapping(value = "/ediary/parent")
public class ParentController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	
		@Autowired
		private ParentService parentService;

		@Autowired
		private ParentRepository parentRepo;
		
		@Autowired
		private RoleRepository roleRepo;
		
		@Autowired
		private AdminRepository adminRepo;
		
		@Autowired
		private StudentRepository studentRepo;
		
		@Autowired
		ParentCustomValidator parentValidator;

		@InitBinder
		protected void initBinder(final WebDataBinder binder) {
			binder.addValidators(parentValidator);
		}
		
		
		//@Secured("ADMIN")
		@GetMapping("/")
		public ResponseEntity<?> findAllParents() {
			try {
				return new ResponseEntity<>((List<ParentEntity>) parentRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			
			//@Secured("ADMIN")
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id) {
				if (parentRepo.existsById(id) && parentService.isActive(id)) {
					return new ResponseEntity<ParentEntity>(parentRepo.findById(id).get(), HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Parent not found."), HttpStatus.NOT_FOUND);
			}

			
			//@Secured("ADMIN")
			@PostMapping(value = "/")
			public ResponseEntity<?> createNew(@Valid @RequestBody ParentDTO newParent, BindingResult result) {
				if(result.hasErrors()) {
					return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
				} else {
					parentValidator.validate(newParent, result);
				}
				ParentEntity parent = new ParentEntity();
				parent.setDeleted(false);
				parent.setFirstName(newParent.getFirstName());
				parent.setLastName(newParent.getLastName());
				parent.setUsername(newParent.getUsername());
				parent.setPassword(newParent.getPassword());
				parent.setPasswordConfirmed(newParent.getPasswordConfirmed());
				parent.setEmail(newParent.getEmail());
				parent.setChildren(newParent.getChildren());
				parent.setRole(roleRepo.findById(3).get());
				parentRepo.save(parent);
				return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
			}
			
			
			
			//@Secured("ADMIN")
			@PutMapping(value = "/{parentId}")
			public ResponseEntity<?> updateParent(@PathVariable Integer parentId, @Valid @RequestBody ParentDTO newParent, 
					BindingResult result) {
				if(parentRepo.existsById(parentId) && parentService.isActive(parentId)) {
					if (result.hasErrors()) {
						return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
					}
					ParentEntity parent = parentRepo.findById(parentId).get();
					parent.setFirstName(newParent.getFirstName());
					parent.setLastName(newParent.getLastName());
					parent.setUsername(newParent.getUsername());
					parent.setPassword((newParent.getPassword()));
					parent.setEmail(newParent.getEmail());
					parent.setChildren(newParent.getChildren());
					parentRepo.save(parent);
					return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Parent not found."), HttpStatus.NOT_FOUND);
			}
			
			
			//@Secured("ADMIN")
			@DeleteMapping(value = "/{parentId}")
			public ResponseEntity<?> deleteParent(@PathVariable Integer parentId) {
				if(parentRepo.existsById(parentId) && parentService.isActive(parentId)) {
					ParentEntity parent = parentRepo.findById(parentId).get();
					parent.setDeleted(true);
					parentRepo.save(parent);
					return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Parent not found."), HttpStatus.NOT_FOUND);
			}
			
			
			//@Secured({"ADMIN", "PARENT"})
			@GetMapping(value = "/{parentId}/children/")
			public ResponseEntity<?> getAllChildren(@PathVariable Integer parentId) {
				if(parentRepo.existsById(parentId) && parentService.isActive(parentId)) {
					ParentEntity parent = parentRepo.findById(parentId).get();
					List<StudentEntity> children = ((List<StudentEntity>) studentRepo.findByParent(parent));
					return new ResponseEntity<List<StudentEntity>>(children, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Parent not found."), HttpStatus.NOT_FOUND);
			}
			
			public String createErrorMessage(BindingResult result) {
				String errors = "";
				for (ObjectError error : result.getAllErrors()) {
					errors += error.getDefaultMessage();
					errors += "\n";
				}
				return errors;
			}

		}
		
		
		