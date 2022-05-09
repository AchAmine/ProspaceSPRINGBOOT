package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.service.IServiceBadwords;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="profanity management")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/profanity")
public class BadwordsController {

	@Autowired
	private IServiceBadwords serviceBadwords;
	
	@ApiOperation(value = "Add badwords")
	@PostMapping("/add-badwords") 
	public void addBadwords(@RequestParam("file") MultipartFile file) {
		
		serviceBadwords.addBadwords(file);
	}
	
}
