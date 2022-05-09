package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Image;
import com.prospace.spring.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ServiceImage implements IServiceImage{

	@Autowired
	ImageRepository imageRepository;
	
	// private final Path root = Paths.get("C:\\wamp64\\www\\FileUploads");
	 private final Path root = Paths.get("C:\\xampp\\htdocs\\FileUploads");

	@Override
	public Image addImage(Image I) {
		
		return imageRepository.save(I);
	}

	@Override
	public Image retrieveImage(Long id) {
		
		return imageRepository.findById(id).orElse(null);
	}

	@Override
	public void save(MultipartFile file) {
		 
		 if (!root.toFile().exists()) {
		try {
		      Files.createDirectory(root);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		 }
		try {
		      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
	}

}
