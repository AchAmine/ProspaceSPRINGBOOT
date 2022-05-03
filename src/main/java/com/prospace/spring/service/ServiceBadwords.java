package com.prospace.spring.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.Badwords;
import com.prospace.spring.repository.BadwordsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceBadwords implements IServiceBadwords{

	@Autowired
	BadwordsRepository badwordsRepository;
	
	 private final Path root = Paths.get("C:\\wamp64\\www\\FileUploads");
	
	@Override
	public void addBadwords(MultipartFile file) {
		log.info("in add function");
		String filename = file.getOriginalFilename();
		getFile(file);
		try  { 
		BufferedReader br = new BufferedReader(new FileReader("C:\\wamp64\\www\\FileUploads\\"+filename));
         String s;
         while ((s = br.readLine()) != null) {
             	log.info("in while loop");
             	if (badwordsRepository.findByWord(s) == null) {
             		log.info("if condition");
            	 Badwords word = new Badwords(s);
            	 log.info("New word"+word.getWord());
            	 badwordsRepository.save(word);
             	}
         }
         br.close();
         log.info("out of while loop");
         File file1 = new File("C:\\wamp64\\www\\FileUploads\\"+filename);
         log.info("File exist? "+file1.exists());
         boolean isDeleted = file1.delete();
         log.info("file is deleted ? : "+isDeleted);
	  } catch (Exception ex) {
	  
	  }
		
	}
	
	public void getFile(MultipartFile file){
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
