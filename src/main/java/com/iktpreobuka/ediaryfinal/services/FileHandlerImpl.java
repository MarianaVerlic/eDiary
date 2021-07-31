package com.iktpreobuka.ediaryfinal.services;

import java.io.File;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class FileHandlerImpl implements FileHandler {
	
	private static String UPLOADED_FOLDER = "C:\\SpringTemp\\";
	
	@Override
	public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
	}
		
	
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");
			
		
		return "redirect:/uploadStatus";

		}
	public File getLogs() {
		String path = "C:\\Users\\Korisnik\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\project_eDiaryFinal\\logs\\spring-boot-logging.log";
		File log = new File(path);
		return log;
	}
}