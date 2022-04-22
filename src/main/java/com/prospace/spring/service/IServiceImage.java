package com.prospace.spring.service;

import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Image;

public interface IServiceImage {

	Image addImage(Image I);
	Image retrieveImage(Long id);
	
	 void save(MultipartFile file);
}
