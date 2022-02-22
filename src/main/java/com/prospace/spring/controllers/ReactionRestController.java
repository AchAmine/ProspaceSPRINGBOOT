package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.prospace.spring.entity.Reaction;
import com.prospace.spring.service.IServiceReaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Article reactions management")
@RequestMapping("/Article_reactions")
public class ReactionRestController {
	

	@Autowired
	IServiceReaction reactionService;
	
	@ApiOperation(value = "Add reaction")
	@PostMapping("/add-reaction/{article-id}/{user-id}") 
	public Reaction addReaction(@RequestBody Reaction react, @PathVariable("article-id") Long articleId,
			@PathVariable("user-id") Long userId) {
		
		return reactionService.addReaction(userId,articleId,react);
	}
	
	@ApiOperation(value = "Delete reaction")
	@DeleteMapping("/remove-reaction/{reaction-id}")
	public void removeReaction(@PathVariable("reaction-id") Long reactionId) {
		reactionService.deleteReaction(reactionId);
	}
	
	@ApiOperation(value = "Update reaction")
	@PutMapping("/modify-reaction")
	public Reaction modifyReaction(@RequestBody  Reaction react) {
		return reactionService.updateReaction(react);
	}
	
	
}
