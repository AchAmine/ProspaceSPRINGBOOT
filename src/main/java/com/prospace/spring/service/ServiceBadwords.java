package com.prospace.spring.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Badwords;
import com.prospace.spring.repository.BadwordsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceBadwords implements IServiceBadwords{

	@Autowired
	BadwordsRepository badwordsRepository;
	
	@Override
	public void addBadwords() {
		log.info("in add function");
		try  { 
		BufferedReader br = new BufferedReader(new FileReader("C:\\Work\\workspace-sts\\prospace-project\\src\\main\\resources\\badwords.txt"));
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
         log.info("out of while loop");
         File file = new File("C:\\Work\\workspace-sts\\prospace-project\\src\\main\\resources\\badwords.txt");
         boolean isDeleted = file.delete();
         log.info("file is deleted ? : "+isDeleted);
	  } catch (Exception ex) {
	  
	  }
		
	}

}
