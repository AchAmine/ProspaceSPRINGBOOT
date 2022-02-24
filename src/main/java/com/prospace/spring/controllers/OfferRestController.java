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

import com.prospace.spring.entity.Offer;
import com.prospace.spring.service.IServiceOffer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Offer management")
@RequestMapping("/Offer")
public class OfferRestController {
	@Autowired
	IServiceOffer offerService;
	
	// http://localhost:8089/SpringMVC/Offer/retrieve-all-offers
		@ApiOperation(value = "Offers list")
		@GetMapping("/retrieve-all-offers")
		public List<Offer> getOffers() {
		List<Offer> listOffers = offerService.retrieveAllOffers();
		return listOffers;
		}
		// http://localhost:8089/SpringMVC/Offer/add-offer

		@ApiOperation(value = "Add offer")
		@PostMapping("/add-offer/{Partner-id}")
		public Offer addOffer(@RequestBody Offer o,@PathVariable("Partner-id") Long id) {
		return offerService.addOffer(o,id);
		}
		
		// http://localhost:8089/SpringMVC/Offer/remove-Offer/{Offer-id}
		@ApiOperation(value = "Delete Offer")
		@DeleteMapping("/remove-Offer/{Offer-id}")
		public void removeOffer(@PathVariable("Offer-id") Long OfferId) {
		offerService.deleteOffer(OfferId);
		}
		
		// http://localhost:8089/SpringMVC/Offer/modify-offer
		@ApiOperation(value = "Update Offer")
		@PutMapping("/modify-offer")
		public Offer modifyOffer(@RequestBody Offer offer) {
		return offerService.updateOffer(offer);
		}
		

		// http://localhost:8089/SpringMVC/Offer/Offer-quizz/8
		@ApiOperation(value = "Get one offer")
		@GetMapping("/retrieve-offer/{offer-id}")
		public Offer retrieveFacture(@PathVariable("offer-id") Long offerId) {
		return offerService.retrieveOffer(offerId);
		}

}
