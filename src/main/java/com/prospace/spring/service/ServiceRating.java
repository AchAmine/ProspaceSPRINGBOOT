package com.prospace.spring.service;

import java.util.List;

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
		Rating oldRating = ratingRepository.findById(rating.getIdRating()).orElse(null);
		rating.setOffer(oldRating.getOffer());
		rating.setUser(oldRating.getUser());
		return ratingRepository.save(rating);
	}
	
	@Override
	public Rating updateRating(Rating rating,Long idOffer) {
		Offer offer= offerRepository.findById(idOffer).orElse(null);
		rating.setOffer(offer);
		return ratingRepository.save(rating);
	}
	
	
	@Override
	public List<Rating> retrieveOfferRatings(Long OfferId) {
		Offer offer = offerRepository.findById(OfferId).orElse(null);
		List<Rating> ratings =  ratingRepository.findByOffer(offer);
		
		return ratings;
	}
	
	@Override
	public Rating retrieveUserOfferRating(Long OfferId, Long UserId){
		
		User user = userRepository.findById(UserId).orElse(null);
		Offer offer = offerRepository.findById(OfferId).orElse(null);
		Rating rating = ratingRepository.findByUserAndOffer(user, offer);
		return  rating ;
	}
	@Override 
	public int AVGOffer( Long offerid){
		if (ratingRepository.AVGOffer(offerid) !=  0.0f){
			return (int)ratingRepository.AVGOffer(offerid);
			
		}
		
		return 0;
	}

}
