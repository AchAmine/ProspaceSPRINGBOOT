package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.Event_Comment;

public interface IServiceEvent_Comment {

	void deleteComment(Long id);
	Event_Comment updateComment(Event_Comment comment);
	
	List<Event_Comment> retrieveEventComments(Long idEvent);
	Event_Comment addComment(Long userId, Long idEvent, Event_Comment comment) ;
	
}
