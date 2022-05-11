package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.OfferComment;

public interface IServiceOfferComment {
	OfferComment addOfferComment(Long userId , Long offerId , OfferComment comment);
	void deleteOfferComment(Long id);
	OfferComment updateOfferComment(OfferComment comment);
	
	List<OfferComment> retrieveOfferComments(Long offerId);
	public OfferComment addOfferCommentReply(Long commentId, Long userId, OfferComment comment);

}
