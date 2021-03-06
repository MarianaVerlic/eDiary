package com.iktpreobuka.ediaryfinal.controllers;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iktpreobuka.ediaryfinal.services.FileHandler;



@Controller
@RequestMapping("/")
public class UploadController {
	
	private Logger logger=(Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileHandler fileHandler;
	
	
	@RequestMapping(method = RequestMethod.GET)
		public String index() {
		return "upload";
	}
	
	@RequestMapping(value = "/uploadStatus", method = RequestMethod.GET)
	public String uploadStatus() {
	return "uploadStatus";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/upload")
	public String uploadFile(@RequestParam ("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		String result = null;
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		result=fileHandler.singleFileUpload(file, redirectAttributes);
		return result;
		
	}
	
		

			
		/*
			
			//@Secured("ROLE_ADMIN")
			@RequestMapping(method = RequestMethod.GET, value = "/upload")
			public boolean singleFileUpload(@RequestParam ("file") MultipartFile file) {	
				@SuppressWarnings("null")
				boolean result = (Boolean) null;
				try {
					result = fileHandler.singleFileUpload(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}*/
		}
		
	


