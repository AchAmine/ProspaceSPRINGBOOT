package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.OfferRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ServiceOffer implements IServiceOffer{
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Offer> retrieveAllOffers() {
		return (List<Offer>) offerRepository.findAll();
	}

	@Override
	public Offer addOffer(Offer o, Long idUser) {
		User partner =userRepository.findById(idUser).orElse(null);
		
		o.setPartner(partner);
		return offerRepository.save(o);
	}

	@Override
	public void deleteOffer(Long id) {
		offerRepository.deleteById(id);
		
	}

	@Override
	public Offer updateOffer(Offer o) {
		
		return offerRepository.save(o);
	}
	
	@Override
	public Offer retrieveOffer(Long id) {
		return offerRepository.findById(id).orElse(null);

	}

}