package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Rating;

public interface IServiceRating {
	Rating addRating(Long userId , Long offerId , Rating rating);
	void deleteRating(Long id);
	Rating updateRating(Rating rating);
	public List<Rating> retrieveOfferRatings(Long OfferId);
	
}
