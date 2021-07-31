package com.iktpreobuka.ediaryfinal.controllers.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RESTError {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


	private String message;
	
	public RESTError(String message) {
		super();
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}
}
