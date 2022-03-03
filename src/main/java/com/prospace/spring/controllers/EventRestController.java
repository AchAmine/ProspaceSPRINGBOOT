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

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;
import com.prospace.spring.entity.Eventdto;
import com.prospace.spring.service.IServiceEvent;



@RestController
@RequestMapping("/Event")
public class EventRestController {
	
	@Autowired
	IServiceEvent eventService;
	

	@PostMapping("/add-event/{user-id}/{activity-id}") 
	public Event addEvent(@RequestBody Event e, @PathVariable("user-id") Long userId, @PathVariable("activity-id") Long ActId) {
		
		return eventService.addEvent1(e,userId,ActId);
	}
	

	@DeleteMapping("/remove-event/{event-id}")
	public void removeEvent(@PathVariable("event-id") Long idEvent) {
        eventService.deleteEvent(idEvent);
	}
	

	@PutMapping("/modify-event")
	public Event modifyEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}
	
	

    @GetMapping("/retrieve-all-events")
    public List<Event> getEvent(){
	List<Event> listEvent = eventService.retrieveAllEvent();
	return listEvent;
}
    
	@GetMapping("/retrieve-event/{event-id}")
	public Event retrieveEvent(@PathVariable("event-id") Long idEvent) {
		return eventService.retrieveEvent(idEvent);
	}
	
	@PutMapping("/participerEvent/{ide}/{idu}")
	public void participerEvent(@PathVariable("ide") Long idEvent,@PathVariable("idu") Long idUser) {
		eventService.participerEvent(idEvent, idUser);
	}
	
	
    @GetMapping("/retrieve-top_event")
    public Eventdto PeriodeEvent() {
    	return eventService.PeriodeEvent();
    }
    
    @GetMapping("/budget/{id}")
	public float budgetEvent(@PathVariable("id") Long id) {
		return   eventService.budgetEvent(id);
	}
    
    @GetMapping("/meilleur_event_profit")
	public EventDt affichermeilleurprofit() {
		return eventService.affichermeilleurprofit();
	}

}
	
	
	


