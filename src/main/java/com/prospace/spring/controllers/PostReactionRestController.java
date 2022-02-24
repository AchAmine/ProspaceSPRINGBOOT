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

import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.postReactionType;
import com.prospace.spring.service.IServicePost_reaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Post reactions ")
@RequestMapping("/Post-Reactions")
public class PostReactionRestController {
	@Autowired
	IServicePost_reaction postReactionService;

	@ApiOperation(value = "Add post reaction")
	@PostMapping("/add-reaction/{post-id}/{user-id}")
	public Post_Reaction addPost_Reaction(@RequestBody Post_Reaction reaction, @PathVariable("post-id") Long postId,
			@PathVariable("user-id") Long userId) {

		return postReactionService.addPost_Reaction(userId, postId, reaction);
	}

	@ApiOperation(value = "Delete post reaction")
	@DeleteMapping("/delete-reaction/{reaction-id}")
	public void removeReaction(@PathVariable("reaction-id") Long reactionId) {
		postReactionService.deletePostReaction(reactionId);
	}

	@ApiOperation(value = "Update post reaction")
	@PutMapping("/update-reaction")
	public Post_Reaction modifyReaction(@RequestBody Post_Reaction react) {
		return postReactionService.updatePostReaction(react);
	}

	@ApiOperation(value = "retrieve post's reactions")
	@GetMapping("/retrieve-postreactions/{post-id}")
	public List<Post_Reaction> retrievePosReactions(@PathVariable("post-id") Long postId) {

		return postReactionService.retrievePostReactions(postId);
	}

	@ApiOperation(value = "retrieve post's reactors")
	@GetMapping("/retrieve-Reactors/{post-id}")
	public List<User> retrievePostReactors(@PathVariable("post-id") Long postId) {

		return postReactionService.retrievePostReactors(postId);
	}

	@ApiOperation(value = "retrieve posts reactors by reaction type")
	@GetMapping("/retrieve-reactors/{post-id}/type/{reaction-type}")
	public List<User> retrievePostReactorsByType(@PathVariable("post-id") Long postId,
			@PathVariable("reaction-type") postReactionType pReactionType) {

		return postReactionService.retrievePostReactorsByType(postId, pReactionType);
	}

}
