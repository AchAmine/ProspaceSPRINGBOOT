package com.prospace.spring.service;

import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.Vote;
import com.prospace.spring.repository.ActivityRepository;
import com.prospace.spring.repository.EventRepository;
import com.prospace.spring.repository.UserRepository;
import com.prospace.spring.repository.VoteRepository;

@Service
public class ServiceEvent  implements IServiceEvent{

	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VoteRepository voteRepository ;

	
	
	@Autowired
	ActivityRepository activityRepository;
	
	
	public Event addEvent1(Event e, Long idUser)  {
			
		User user = userRepository.findById(idUser).orElse(null);
	

	
		e.setUser_organizer(user);
		
	
	eventRepository.save(e);
	return e;
	
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


//	@Override
//	public List<Event> EventDays(LocalDate StartAt) {
		// TODO Auto-generated method stub
//		return eventRepository.EventDays(StartAt) ;
	@Override
public void SendMail(Event e,Long i){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("belaidmidouu@gmail.com");
    	mailSender.setPassword("moudazapa");
    	 
    	Properties properties = new Properties();
    	properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	properties.setProperty("mail.smtp.auth", "true");
    	properties.setProperty("mail.smtp.starttls.enable", "true");
    	 User u  = userRepository.findById(i).get();
    	mailSender.setJavaMailProperties(properties);
    	String from = mailSender.getUsername();
    	String to = u.getEmail();
    	 
    	SimpleMailMessage message = new SimpleMailMessage();
    	 
    	message.setFrom(from);    	
    	message.setTo(to);
    	message.setSubject("This is a plain text email");
        message.setText("there is an upcoming event check the details"+e.getDescription());
  
    	
    	mailSender.send(message);
    	
    }


	@Override
	public void voterEvent(Long eventId, Long userId, float note) {
		Event e= eventRepository.findById(eventId).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+eventId));
		User u= userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("no employee with id ="+userId));
		Vote vote=new Vote();
		vote.setNote(note);
		vote.setUser(u);
		vote.setEvent(e);
		voteRepository.save(vote);
	}


	@Override
	public Float getMoyenneVote(Long eventId) {
		Event e= eventRepository.findById(eventId).orElseThrow(()->new IllegalArgumentException("no voyage with id ="+eventId));
		if(e.getVotes().size()==0){
			return (float) 0;
		}
		float res=0;
		
			for (Vote vote : e.getVotes()) {
				res+=vote.getNote();
			}
		res=res/e.getVotes().size();
		
		return res;
	}
	

	}




