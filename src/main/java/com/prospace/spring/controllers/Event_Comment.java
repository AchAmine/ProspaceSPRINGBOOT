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

import com.prospace.spring.entity.Article_Comment;

import com.prospace.spring.service.IServiceEvent_Comment;



@RestController
@RequestMapping("/Event_comments")
public class Event_Comment {

	@Autowired
	IServiceEvent_Comment event_commentService;
	
	@PostMapping("/add-comment/{event-id}/{user-id}") 
	public void addComment(@RequestBody com.prospace.spring.entity.Event_Comment comment, @PathVariable("event-id") Long idEvent,@PathVariable("user-id") Long userId) {
		
	 event_commentService.addComment(userId, idEvent,comment );
	}

	@DeleteMapping("/remove-comment/{comment-id}")
	public void removeComment(@PathVariable("comment-id") Long commentId) {
		event_commentService.deleteComment(commentId);
	}
	
	@PutMapping("/modify-comment")
	public com.prospace.spring.entity.Event_Comment modifyComment(@RequestBody  com.prospace.spring.entity.Event_Comment comment) {
		return event_commentService.updateComment(comment);
	}
	
	@GetMapping("/retrieve-eventcomments/{event-id}") 
	public List<com.prospace.spring.entity.Event_Comment> retrieveEventComments(@PathVariable("event-id") Long idEvent) {
		
		return event_commentService.retrieveEventComments(idEvent);
	}
}
