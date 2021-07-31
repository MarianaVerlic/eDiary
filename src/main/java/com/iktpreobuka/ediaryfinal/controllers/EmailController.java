package com.iktpreobuka.ediaryfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.ediaryfinal.models.EmailObject;
import com.iktpreobuka.ediaryfinal.services.EmailService;


@RestController
@RequestMapping(path="/")
public class EmailController {

	
	@Autowired
	private EmailService emailService;
	
	private
	static String PATH_TO_ATTACHMENT = "C:\\Users\\Korisnik\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\project_eDiaryFinal\\logs\\spring-boot-logging.log";
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/simpleEmail")
	public String sendSimpleEmail(@RequestBody EmailObject emailObject) {
		if(emailObject==null || emailObject.getTo()==null || emailObject.getText()==null) {
			return null;
		}
	emailService.sendSimpleEmail(emailObject);
	return "Your mail has been sent!";
	}
	
	@PostMapping(value = "/templateEmail")
	public String sendTemplateMessage(@RequestBody EmailObject emailObject) throws Exception{
		if(emailObject==null || emailObject.getTo()==null || emailObject.getText()==null) {
			return null;
		}
	emailService.sendTemplateMessage(emailObject);
	return "Your mail has been sent!";
	}
	

	
	@PostMapping(value = "/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailObject emailObject) throws Exception{
		if(emailObject==null || emailObject.getTo()==null || emailObject.getText()==null) {
			return null;
		}
	emailService.sendMessageWithAttachment(emailObject, PATH_TO_ATTACHMENT);
	return "Your mail has been sent!";
	}
	
	
}
	
