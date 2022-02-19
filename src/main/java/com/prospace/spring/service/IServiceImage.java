package com.prospace.spring.service;

import com.prospace.spring.entity.Image;

public interface IServiceImage {

	Image addImage(Image I);
	Image retrieveImage(Long id);
}
