package com.prospace.spring.controllers;

import java.util.HashMap;
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

import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.ReactionType;
import com.prospace.spring.entity.User;
import com.prospace.spring.service.IServiceReaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Article reactions management")
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@ApiOperation(value = "retrieve article's reactions")
	@GetMapping("/retrieve-articlereactions/{article-id}") 
	public List<Reaction> retrieveArticleReactions(@PathVariable("article-id") Long articleId) {
		
		return reactionService.retrieveArticleReactions(articleId);
	}
	
	@ApiOperation(value = "retrieve article's reactors")
	@GetMapping("/retrieve-articlereactors/{article-id}") 
	public List<User> retrieveArticleReactors(@PathVariable("article-id") Long articleId) {
		
		return reactionService.retrieveArticleReactors(articleId);
	}
	
	
	// HashMap 
	@ApiOperation(value = "retrieve users and their reaction")
	@GetMapping("/retrieve-usersreaction/{article-id}") 
	public HashMap<User,ReactionType> retrieveUsersReactions(@PathVariable("article-id") Long articleId) {
		
		return reactionService.retrieveUsersReactions(articleId);
	}
	
	@ApiOperation(value = "retrieve article's reactors")
	@GetMapping("/retrieve-articlereactors/{article-id}/type/{reaction-type}") 
	public List<User> retrieveArticleReactorsByType(@PathVariable("article-id") Long articleId , 
			@PathVariable("reaction-type") ReactionType reactionType) {
		
		return reactionService.retrieveArticleReactorsByType(articleId,reactionType);
	}
	
	
}
