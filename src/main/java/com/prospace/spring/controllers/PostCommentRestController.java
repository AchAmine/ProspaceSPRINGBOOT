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

import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.service.IServicePost_Comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(tags = "Post comments management")
@RequestMapping("/Post_comments")
public class PostCommentRestController {
	@Autowired
	IServicePost_Comment post_commentService;
	

	
	@ApiOperation(value = "Add comment  ")
	@PostMapping("/add-comment/{user-id}/{post-id}")
	public Post_Comment addComment(@RequestBody Post_Comment comment,@PathVariable("user-id") Long userId, @PathVariable("post-id") Long postId
			) {
		String str = post_commentService.GetCensoredText(comment.getContent());

		comment.setContent(str);
		return post_commentService.addComment( comment,userId, postId);
	}

	@ApiOperation(value = "Delete comment")
	@DeleteMapping("/delete-comment/{comment-id}")
	public void deleteComment(@PathVariable("comment-id") Long commentId) {
		post_commentService.deleteComment(commentId);
	}

	@ApiOperation(value = "Update comment")
	@PutMapping("/modify-comment")
	public Post_Comment modifyComment(@RequestBody Post_Comment comment) {
		return post_commentService.updateComment(comment);
	}

	@ApiOperation(value = "retrieve post's comments")

	@GetMapping("/retrieve-postcomments/{post-id}")
	public List<Post_Comment> retrievePostComments(@PathVariable("post-id") Long postId) {

		return post_commentService.retrievePostComments(postId);
	}

	/********************* COMMENTS REPLIES *******/

	@ApiOperation(value = "Add comment REPLY ")
	@PostMapping("/add-comment-reply/{comment-id}/{user-id}")
	public Post_Comment addCommentReply(@RequestBody Post_Comment comment, @PathVariable("comment-id") Long commentId,
			@PathVariable("user-id") Long userId) {
		String str = post_commentService.GetCensoredText(comment.getContent());

		comment.setContent(str);
		return post_commentService.addCommentReply(userId, commentId, comment);
	}

	@ApiOperation(value = "retrieve post comments replies")

	@GetMapping("/retrieve-postcomments-replies/{comment-id}")
	public List<Post_Comment> retrievePostCommentReplies(@PathVariable("comment-id") Long postId) {

		return post_commentService.retrievePostCommentReplies(postId);
	}
	@ApiOperation(value = "Update comment reply")
	@PutMapping("/modify-comment-reply")
	public Post_Comment modifyCommentReply(@RequestBody Post_Comment comment) {
		return post_commentService.updateCommentReply(comment);
	}
	
	




	
	
}
