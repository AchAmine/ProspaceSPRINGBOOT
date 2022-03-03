package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.Event_Comment;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.Article_CommentRepository;
import com.prospace.spring.repository.EventRepository;
import com.prospace.spring.repository.Event_CommentRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceEvent_Comment implements  IServiceEvent_Comment{
	
	@Autowired
	Event_CommentRepository event_commentRepository ; 
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Event_Comment addComment(Long userId, Long idEvent, Event_Comment comment) {
		Event event = eventRepository.findById(idEvent).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
	
		comment.setUser(user);
		comment.setEvent(event);
		
		return event_commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Event_Comment comment = event_commentRepository.findById(id).orElse(null);
		event_commentRepository.delete(comment);	
		
	}

	@Override
	public Event_Comment updateComment(Event_Comment comment) {
		return event_commentRepository.save(comment);
	}

	@Override
	public List<Event_Comment> retrieveEventComments(Long idEvent) {
		Event event = eventRepository.findById(idEvent).orElse(null);
		List<Event_Comment> comments =  event_commentRepository.findByEvent(event);
		
		return comments;
	}

	



}
