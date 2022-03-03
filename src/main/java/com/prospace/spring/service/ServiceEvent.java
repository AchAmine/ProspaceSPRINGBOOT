package com.prospace.spring.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Activity;

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;
import com.prospace.spring.entity.Eventdto;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ActivityRepository;
import com.prospace.spring.repository.EventRepository;

import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceEvent implements IServiceEvent {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	
	
	@Autowired
	ActivityRepository activityRepository;
	@Transactional
	public Event addEvent1(Event e, Long idUser, Long idActivity) {
			
		User user = userRepository.findById(idUser).orElse(null);
		Activity act = activityRepository.findById(idActivity).get();

		user.getEventsOrganized().add(e);
	
		e.setUser_organizer(user);
	
	    e.getActivities().add(act);
		return eventRepository.save(e);
		
	}
	

	@Override
	public void deleteEvent(Long id) {
		Event event = eventRepository.findById(id).orElse(null);
		eventRepository.delete(event);
		
	}

	@Override
	public Event updateEvent(Event e) {
		return eventRepository.save(e);
	}

	@Override
	public List<Event> retrieveAllEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Event retrieveEvent(Long id) {
		return eventRepository.findById(id).orElse(null);
	}

	@Override
	public void participerEvent(Long idEvent, Long idUser) {
		
		User u = userRepository.findById(idUser).orElse(null);
		Event e = eventRepository.findById(idEvent).orElse(null);
		e.getParticipants().add(u);
		eventRepository.save(e);
		
	}

	@Override
	public Eventdto PeriodeEvent() {
	  return	eventRepository.PeriodeEvent();

	}

	@Override
	public float budgetEvent(Long id) {
	
			
			Event e=eventRepository.findById(id).orElseGet(null);
			Float result= eventRepository.countusers(id)*e.getPrice();
			
			Float budgetnet = result-e.getDepenses();
			e.setProfit(budgetnet);
			eventRepository.save(e);
			
		    return  budgetnet;}

	@Override
public EventDt affichermeilleurprofit() {
		
		EventDt evtDt = null;
		for (Event e:eventRepository.findAll()) {
			if (e.getProfit()==0) {
				budgetEvent(e.getIdEvent());
				eventRepository.save(e);}
				
		evtDt= eventRepository.affichermeilleurprofitevent();}
		
	
		return evtDt;
	

	
		
		
	}
	  
		
	}
	



//	@Override
//	public List<Event> EventDays(LocalDate StartAt) {
		// TODO Auto-generated method stub
//		return eventRepository.EventDays(StartAt) ;
//	}

	
	


	

	




