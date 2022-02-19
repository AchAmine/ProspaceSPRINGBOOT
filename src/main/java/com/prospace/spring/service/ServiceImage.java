package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Image;
import com.prospace.spring.repository.ImageRepository;

@Service
public class ServiceImage implements IServiceImage{

	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public Image addImage(Image I) {
		
		return imageRepository.save(I);
	}

	@Override
	public Image retrieveImage(Long id) {
		
		return imageRepository.findById(id).orElse(null);
	}

}
