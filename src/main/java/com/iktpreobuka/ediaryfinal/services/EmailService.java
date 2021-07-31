package com.iktpreobuka.ediaryfinal.services;

import com.iktpreobuka.ediaryfinal.models.EmailObject;

public interface EmailService {
	

	public void sendSimpleEmail(EmailObject emailObject);
	
	public void sendTemplateMessage(EmailObject emailObject) throws Exception;
	
	public void sendMessageWithAttachment(EmailObject emailObject, String pathToAttachment) throws Exception;
	
	
	
	
}
