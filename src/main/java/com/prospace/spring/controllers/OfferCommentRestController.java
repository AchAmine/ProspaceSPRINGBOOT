package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.OfferComment;
import com.prospace.spring.service.IServiceOfferComment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Offer comments management")
@RequestMapping("/OfferComments")
@CrossOrigin(origins = "http://localhost:4200")
public class OfferCommentRestController {
	

	@Autowired
	IServiceOfferComment offeCommentService;
	
	@ApiOperation(value = "Add offer comment")
	@PostMapping("/add-offer-comment/{offer-id}/{user-id}") 
	public OfferComment addOfferComment(@RequestBody OfferComment comment, @PathVariable("offer-id") Long offerId,
			@PathVariable("user-id") Long userId) {
		
		return offeCommentService.addOfferComment(userId, offerId, comment);
	}
	
	
	@ApiOperation(value = "Delete offer comment")
	@DeleteMapping("/remove-offer-comment/{comment-id}")
	public void deleteOfferComment(@PathVariable("comment-id") Long commentId) {
		offeCommentService.deleteOfferComment(commentId);
	}
	
	@ApiOperation(value = "Update offer comment")
	@PutMapping("/modify-offer-comment")
	public OfferComment updateOfferComment(@RequestBody  OfferComment comment) {
		return offeCommentService.updateOfferComment(comment);
	}
	
	@ApiOperation(value = "retrieve offer comments")
	@GetMapping("/retrieve-offer-comments/{offer-id}") 
	public List<OfferComment> retrieveOfferComments(@PathVariable("offer-id") Long offerId) {
		
		return offeCommentService.retrieveOfferComments(offerId);
	}
	
	@ApiOperation(value = "Add Offer comment reply ")
	@PostMapping("/add-offer-comment-reply/{offer-comment-id}/{user-id}")
	public OfferComment addCommentReply(@RequestBody OfferComment offercomment, @PathVariable("offer-comment-id") Long offercommentId,
			@PathVariable("user-id") Long userId) {

		return offeCommentService.addOfferCommentReply( offercommentId,userId , offercomment);
	}


	
}
