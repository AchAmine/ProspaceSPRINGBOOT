package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Rating;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.OfferRepository;
import com.prospace.spring.repository.RatingRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceRating implements IServiceRating {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating addRating(Long userId, Long offerId, Rating rating) {
		User user = userRepository.findById(userId).orElse(null);
		Offer offer = offerRepository.findById(offerId).orElse(null);
		rating.setUser(user);
		rating.setOffer(offer);
		return ratingRepository.save(rating);
	}

	@Override
	public void deleteRating(Long id) {
		ratingRepository.deleteById(id);
		
	}

	@Override
	public Rating updateRating(Rating rating) {
		return ratingRepository.save(rating);
	}

}
