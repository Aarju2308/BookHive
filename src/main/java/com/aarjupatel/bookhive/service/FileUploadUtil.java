package com.aarjupatel.bookhive.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtil {
	
	public static void saveFile(String fileName, MultipartFile file) {
		
		Path uploadPath = Paths.get("/Users/aarjupatel/Downloads/Test Spring/bookhive/src/main/resources/static/img/BookCovers");
		
		try (InputStream inputStream = file.getInputStream()){
			
			Path filePath = uploadPath.resolve(fileName);
			
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
}
