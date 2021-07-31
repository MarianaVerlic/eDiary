package com.iktpreobuka.ediaryfinal.services;

import java.io.File;




import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.models.EmailObject;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;
	
	@Override
	public void sendSimpleEmail(EmailObject emailObject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailObject.getTo());
		message.setSubject(emailObject.getSubject());
		message.setText(emailObject.getText());
		emailSender.send(message);
		}
	
	
	@Override
	public void sendTemplateMessage (EmailObject emailObject) throws Exception {
	MimeMessage mail = emailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	helper.setTo(emailObject.getTo());
	helper.setSubject(emailObject.getSubject());
	String text = "<html><body><table"+"style='border:2px solid black'>"+"<tr><td>"+emailObject.getText()+"<tr/><td/>"
	+"</table><body><html>";
	helper.setText(text, true);
	emailSender.send(mail);
	}
	
	@Override
	public void sendMessageWithAttachment (EmailObject emailObject, String pathToAttachment) throws Exception {
	MimeMessage mail = emailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	helper.setTo(emailObject.getTo());
	helper.setSubject(emailObject.getSubject());
	helper.setText(emailObject.getText(), false);
	FileSystemResource file= new FileSystemResource (new File(pathToAttachment));
	helper.addAttachment(file.getFilename(), file);
	emailSender.send(mail);
	}
	
	
	
	}

	
