package com.prospace.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Partner;


public interface IServiceOffer {
	List<Offer> retrieveAllOffers();
	Offer addOffer(Offer o,Long idUser,MultipartFile file);
	void deleteOffer(Long id);
	Offer updateOffer(Offer o);
	Offer retrieveOffer(Long id);
	public List<Offer> TriOffers();
	void addOffers(List<Offer> Offers);
	
	void uploadFile(MultipartFile file);

	
}

