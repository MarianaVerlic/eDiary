package com.iktpreobuka.ediaryfinal.controllers;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.ediaryfinal.controllers.util.RESTError;
import com.iktpreobuka.ediaryfinal.controllers.util.Validation;
import com.iktpreobuka.ediaryfinal.entities.AdminEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.UserEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.AdminDTO;
import com.iktpreobuka.ediaryfinal.entities.dto.TeacherDTO;
import com.iktpreobuka.ediaryfinal.entities.enums.Roles;
import com.iktpreobuka.ediaryfinal.repositories.AdminRepository;
import com.iktpreobuka.ediaryfinal.repositories.RoleRepository;
import com.iktpreobuka.ediaryfinal.security.Views;
import com.iktpreobuka.ediaryfinal.services.AdminService;
import com.iktpreobuka.ediaryfinal.services.FileHandler;
import com.iktpreobuka.ediaryfinal.utils.AdminCustomValidator;
import com.iktpreobuka.ediaryfinal.utils.TeacherCustomValidator;



@RestController
@RequestMapping(value = "/ediary/admin")
public class AdminController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private AdminCustomValidator validator;
	
	@InitBinder("AdminDTO")
	protected void initBinder(final WebDataBinder binder) {
	binder.addValidators(validator);
	}

	//@Value("${spring.security.secret-key}")
	//private String secretKey;
	
	//@Value("${spring.security.token-duration}")
	//private Integer tokenDuration;

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	
	@GetMapping("/")
		public ResponseEntity<?> findAllAdmins() {
			try {
				return new ResponseEntity<>((List<AdminEntity>) adminRepo.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(
						new RESTError( "Exception occurred: " + e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	
	
	
	
			//@Secured({"ADMIN"})
			@GetMapping(value = "/{id}")
			public ResponseEntity<?> getById(@PathVariable Integer id, HttpServletRequest request) {
				AdminEntity admin = adminRepo.findById(id).get();
				if (adminRepo.existsById(id) && adminService.isActive(id)) {
					return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Admin not found."), HttpStatus.NOT_FOUND);
			}

			
			//@Secured("ADMIN")
			@PostMapping(value = "/")
			public ResponseEntity<?> create(@Valid @RequestBody AdminDTO newAdmin, BindingResult result) {
				if (result.hasErrors()) {
					return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
				} else {
					validator.validate(newAdmin, result);
				}
				AdminEntity admin = new AdminEntity();
				admin.setDeleted(false);
				admin.setFirstName(newAdmin.getFirstName());
				admin.setLastName(newAdmin.getLastName());
				admin.setUsername(newAdmin.getUsername());
				admin.setPassword(newAdmin.getPassword());
				admin.setPasswordConfirmed(newAdmin.getPasswordConfirmed());
				admin.setRole(roleRepo.findById(1).get());
				adminRepo.save(admin);
				return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
			}
			
			
			//@Secured("ADMIN")
			@PutMapping(value = "/{adminId}")
			public ResponseEntity<?> update(@PathVariable Integer adminId, @Valid @RequestBody AdminDTO newAdmin, 
					BindingResult result) {
				if(adminRepo.existsById(adminId) && adminService.isActive(adminId)) {
					if (result.hasErrors()) {
						return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
					}
					AdminEntity admin = adminRepo.findById(adminId).get();
					admin.setFirstName(newAdmin.getFirstName());
					admin.setLastName(newAdmin.getLastName());
					admin.setUsername(newAdmin.getUsername());
					adminRepo.save(admin);
	
					return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);
			}
			
			
			//@Secured("ADMIN")
			@DeleteMapping(value = "/{adminId}")
			public ResponseEntity<?> delete(@PathVariable Integer adminId) {
				if(adminRepo.existsById(adminId) && adminService.isActive(adminId)) {
					AdminEntity admin = adminRepo.findById(adminId).get();
					admin.setDeleted(true);
					adminRepo.save(admin);
					return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError("Teacher not found."), HttpStatus.NOT_FOUND);
			}

	/*@PostMapping(value = "login")
	public ResponseEntity<Object> login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().iterator().next().getAuthority();
		Integer id = adminService.findIdByUsername(auth.getName());
		logger.info("Log in request with username: " + auth.getName());
		if(id != null) {
			return new ResponseEntity<>("{\"role\":\"" + role + "\", \"id\":\"" + id + "\"}", HttpStatus.OK);
		}
		return new ResponseEntity<>("{\"role\":\"" + role + "\"}", HttpStatus.OK);
	}*/
	
	//@Secured("ADMIN")
	@GetMapping(value = "usernames")
	public ResponseEntity<List<String>> getAllUsernames() {
		return new ResponseEntity<List<String>>(this.adminService.getAllUsernames(), HttpStatus.OK);
	}
	
	//@Secured("ADMIN")
	@PostMapping(value = "/download")
	public ResponseEntity<Resource> downloadLogs() {
		try {
			File file = fileHandler.getLogs();
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.add("content-disposition", "attachment; filename=" + "logs.txt");
	     
			return ResponseEntity.ok()
		            .headers(responseHeaders)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType("application/octet-stream"))
		            .body(resource);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}

	/*private String getJWTToken(UserEntity user)	{
		List<GrantedAuthority> grantedAuthorities = 
				AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getRoleName());
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getEmail())
				.claim("authorities", grantedAuthorities.stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
				return "Bearer " + token;
				
					
}*/
	
	}
	
	