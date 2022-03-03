package com.prospace.spring.service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;
import com.prospace.spring.entity.Eventdto;

public interface IServiceEvent {
	Event addEvent1(Event e,Long idUser, Long idActivity);
	void deleteEvent(Long id);
	Event updateEvent(Event e);
	List<Event> retrieveAllEvent();
	Event retrieveEvent(Long id);
	void participerEvent(Long idEvent,Long idUser);
	Eventdto PeriodeEvent();
	public float budgetEvent(Long id);
	
	public EventDt affichermeilleurprofit();
//	List<Event> getEventsByUser(Long idUser);
//	List<Event> EventDays(LocalDate StartAt);
	

}
