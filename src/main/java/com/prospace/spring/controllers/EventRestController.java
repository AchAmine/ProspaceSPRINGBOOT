package com.prospace.spring.controllers;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;

import com.prospace.spring.service.IServiceEvent;
import com.prospace.spring.service.PDFExporter;










@RestController
@RequestMapping("/Event")
public class EventRestController {
	
	@Autowired
	IServiceEvent eventService;
	
	

	@PostMapping("/add-event/{user-id}") 
	public Event addEvent(@RequestBody Event e, @PathVariable("user-id") Long userId)  {
		eventService.SendMail(e,userId);
		
			return eventService.addEvent1(e,userId);
			
			
	
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
	@GetMapping("/retrieve-export-pdf")
    public List<Event> exportPDF(HttpServletResponse response) throws DocumentException, IOException{
	 response.setContentType("application/pdf");
     DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
     String currentDateTime = dateFormatter.format(new Date());
      
     String headerKey = "Content-Disposition";
     String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
     response.setHeader(headerKey, headerValue);
      
 	List<Event> listEvent = eventService.retrieveAllEvent();
      
     PDFExporter exporter = new PDFExporter(listEvent);
     exporter.export(response);
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
	
	

    
    @GetMapping("/budget/{id}")
	public float budgetEvent(@PathVariable("id") Long id) {
		return   eventService.budgetEvent(id);
	}
    
    @GetMapping("/meilleur_event_profit")
	public EventDt affichermeilleurprofit() {
		return eventService.affichermeilleurprofit();
	}

    
	@PostMapping("/add-vote/{eventId}/{userId}/{note}")
	public void addVote(@PathVariable("eventId") Long eventId,@PathVariable("userId") Long userId,@PathVariable("note") int note)  {
	
		eventService.voterEvent(eventId, userId, note);
	}   
    
	@GetMapping("/getMoyenneNote/{eventId}")
	public Float getMoyenne(@PathVariable("eventId") Long eventId ){
		return eventService.getMoyenneVote(eventId);
	}
	
}



	
	


