package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;

public interface IServiceEvent {

	
	Event addEvent1(Event e,Long idUser) ;
	void deleteEvent(Long id);
	Event updateEvent(Event e);
	List<Event> retrieveAllEvent();
	Event retrieveEvent(Long id);
	void participerEvent(Long idEvent,Long idUser);
	void SendMail(Event e,Long i);
	public float budgetEvent(Long id);
	
	public EventDt affichermeilleurprofit();
	
	void voterEvent(Long eventId,Long userId,float note);
	Float getMoyenneVote(Long eventId);
}
