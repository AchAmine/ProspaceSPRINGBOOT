package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.OfferState;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.OfferRepository;
import com.prospace.spring.repository.PartnerRepository;
import com.prospace.spring.repository.UserRepository;

@Service

public class ServicePartner implements IServicePartner {
	
	@Autowired
	PartnerRepository partnerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired 
	OfferRepository offerRepository;
	@Override
	public List<User> AddAndRetrievePartners() {
		
		/*for(User user : userRepository.findAll()){
			if(user.getUserRole().toString()=="Partner") {
				if (user.getDeletedAt()==null){
					Partner p = new Partner(user,user.getCreatedAt(),1);
					p.setUser(user);
					partnerRepository.save(p);
				}else{
				Partner p = new Partner(user,user.getCreatedAt(), user.getDeletedAt(), 1);
				p.setUser(user);
				partnerRepository.save(p);}
			}*/
		return offerRepository.PartnersList();
	}
	@Override
	public Partner AddPartner(Partner p, Long idUser) {
		
		/*for(User user : userRepository.findAll()){
			if(user.getUserRole().toString()=="Partner") {
				if (user.getDeletedAt()==null){
					Partner p = new Partner(user,user.getCreatedAt(),1);
					p.setUser(user);
					partnerRepository.save(p);
				}else{
				Partner p = new Partner(user,user.getCreatedAt(), user.getDeletedAt(), 1);
				p.setUser(user);
				partnerRepository.save(p);}
			}*/
		//return offerRepository.PartnersList();
		User u = userRepository.findById(idUser).orElse(null);
		p.setUser(u);
		return partnerRepository.save(p);
		
	}
	
	
	

}
