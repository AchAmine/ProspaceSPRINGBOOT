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
@Api(tags = " Forum Reactions Management ")
@RequestMapping("/Forum-Reactions")
public class PostReactionRestController {
	@Autowired
	IServicePost_reaction postReactionService;

	@ApiOperation(value = "Add post reaction")
	@PostMapping("/add-reaction/{post-id}/{user-id}")
	public Post_Reaction addPost_Reaction(@RequestBody Post_Reaction reaction, @PathVariable("post-id") Long postId,
			@PathVariable("user-id") Long userId) {

		return postReactionService.addPost_Reaction(postId, userId, reaction);
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

	/***************************************************************************/

	@ApiOperation(value = "Add post Comment reaction")
	@PostMapping("/add-comment-reaction/{comment-id}/{user-id}")
	public Post_Reaction addPostComment_Reaction(@RequestBody Post_Reaction reaction,
			@PathVariable("comment-id") Long commentId, @PathVariable("user-id") Long userId) {

		return postReactionService.addPostComment_Reaction(userId, commentId, reaction);
	}

	@ApiOperation(value = "Add topic reaction")
	@PostMapping("/add-topic-reaction/{topic-id}/{user-id}")
	public Post_Reaction addTopicReaction(@RequestBody Post_Reaction reaction, @PathVariable("topic-id") Long topicId,
			@PathVariable("user-id") Long userId) {

		return postReactionService.addTopicReaction(userId, topicId, reaction);
	}

	@ApiOperation(value = "Add section reaction")
	@PostMapping("/add-section-reaction/{section-id}/{user-id}")
	public Post_Reaction addSectionReaction(@RequestBody Post_Reaction reaction,
			@PathVariable("section-id") Long sectionId, @PathVariable("user-id") Long userId) {

		return postReactionService.addSectionReaction(userId, sectionId, reaction);
	}

	/********************************************************/

	@ApiOperation(value = "retrieve postComments reactions")
	@GetMapping("/retrieve-postCommentsReactions/{comment-id}")
	public List<Post_Reaction> retrievePostCommentReactions(@PathVariable("comment-id") Long commentId) {

		return postReactionService.retrievePostCommentReactions(commentId);
	}

	@ApiOperation(value = "retrieve section's reactions")
	@GetMapping("/retrieve-sectionReactions/{section-id}")
	public List<Post_Reaction> retrieveSectionReactions(@PathVariable("section-id") Long sectionId) {

		return postReactionService.retrieveSectionReactions(sectionId);
	}

	@ApiOperation(value = "retrieve topic's reactions")
	@GetMapping("/retrieve-topicReactions/{topic-id}")
	public List<Post_Reaction> retrieveTopicReactions(@PathVariable("topic-id") Long topicId) {

		return postReactionService.retrieveTopicReactions(topicId);
	}

	/*********************************************************************/
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

	/******************************************************************/

	@ApiOperation(value = "retrieve by reaction type")
	@GetMapping("/retrieve-byReactions")
	public List<Object[]> sortbyReaction() {
		return postReactionService.SortbyLikes();
	}

}
