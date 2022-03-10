package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Rating;
import com.prospace.spring.service.IServiceRating;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Rating management")
@RequestMapping("/Offer/Rating")
public class RatingRestController {
	@Autowired
	IServiceRating serviceRating;
	
	 @ApiOperation(value = "Add Rating")
		@PostMapping("/add-rating/{user-id}/{offer-id}")
		public Rating addRating(@PathVariable("user-id")Long userId, @PathVariable("offer-id")Long offerId,@RequestBody Rating r) {
		return serviceRating.addRating(userId,offerId,r);
		}
	 
	// http://localhost:8089/SpringMVC/Rating/remove-Rating/{Rating-id}
			@ApiOperation(value = "Delete Rating")
			@DeleteMapping("/remove-Rating/{Rating-id}")
			public void removeRating(@PathVariable("Rating-id") Long RatingId) {
			serviceRating.deleteRating(RatingId);
			}
			
	
			// http://localhost:8089/SpringMVC/Rating/modify-Rating
			@ApiOperation(value = "Update Rating")
			@PutMapping("/modify-rating")
			public Rating modifyRating(@RequestBody Rating rating) {
			return serviceRating.updateRating(rating);
			}
			
			// http://localhost:8089/SpringMVC/Quizz/Quizz-Quizz/8
			@ApiOperation(value = "Retrieve offer ratings")
			@GetMapping("/retrieve-offer-ratings/{offer-id}")
			public List<Rating> retrieveOfferRatings(@PathVariable("offer-id") Long OfferId) {
			return serviceRating.retrieveOfferRatings(OfferId);
			
		 

	}

}
