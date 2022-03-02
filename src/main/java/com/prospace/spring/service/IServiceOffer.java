package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Offer;


public interface IServiceOffer {
	List<Offer> retrieveAllOffers();
	Offer addOffer(Offer o,Long idUser);
	void deleteOffer(Long id);
	Offer updateOffer(Offer o);
	Offer retrieveOffer(Long id);
	public List<Offer> TriOffers();
	void addOffers(List<Offer> Offers);
}
