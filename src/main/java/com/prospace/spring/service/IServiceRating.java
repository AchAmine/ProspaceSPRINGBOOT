package com.prospace.spring.service;

import com.prospace.spring.entity.Rating;

public interface IServiceRating {
	Rating addRating(Long userId , Long offerId , Rating rating);
	void deleteRating(Long id);
	Rating updateRating(Rating rating);
	
}
