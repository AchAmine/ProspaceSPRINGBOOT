package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.OfferComment;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.OfferCommentRepository;
import com.prospace.spring.repository.OfferRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ServiceOfferComment implements IServiceOfferComment{
	@Autowired
	OfferCommentRepository offerCommentRepository ;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public OfferComment addOfferComment(Long userId, Long offerId, OfferComment comment) {
		Offer offer = offerRepository.findById(offerId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		Date date = new Date(System.currentTimeMillis());
		comment.setPostedAt(date);
		comment.setUser(user);
		comment.setOffer(offer);
		
		return offerCommentRepository.save(comment);
	}

	@Override
	public void deleteOfferComment(Long id) {
		OfferComment comment = offerCommentRepository.findById(id).orElse(null);
		offerCommentRepository.delete(comment);			
	}

	@Override
	public OfferComment updateOfferComment(OfferComment comment) {
		OfferComment offerComment = offerCommentRepository.findById(comment.getIdOfferComment()).orElse(null);
		Date date = new Date(System.currentTimeMillis());
		comment.setUpdatedAt(date);
		comment.setPostedAt(offerComment.getPostedAt());
		comment.setOffer(offerComment.getOffer());
		comment.setUser(offerComment.getUser());
		return offerCommentRepository.save(comment);
	}

	@Override
	public List<OfferComment> retrieveOfferComments(Long offerId) {
		Offer offer = offerRepository.findById(offerId).orElse(null);
		List<OfferComment> Offercomments =  offerCommentRepository.findByOffer(offer);
		for(int i = 0 ; i<Offercomments.size();i++) {
			Offercomments.get(i).setContent(Offercomments.get(i).getContent());
		}
		return Offercomments;
	}

}
