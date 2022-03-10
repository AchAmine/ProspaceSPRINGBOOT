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
import com.prospace.spring.entity.TopActiviteInTournament;
import com.prospace.spring.entity.Tournament;
import com.prospace.spring.service.IServiceTournoiii;

@RestController
@RequestMapping("/Tournoi")
public class TournoiRestController {

	@Autowired
	IServiceTournoiii serviceTournoi ;
	
	@PostMapping("/add-tournoi/{id-Activity}") 
	public void addTournoi(@RequestBody com.prospace.spring.entity.Tournament t ,@PathVariable ("id-Activity") Long idActivity ) {
		serviceTournoi.addTournoi(t, idActivity);
	}
	
		
	@GetMapping("/retrieve-all-tournoi")
	public List<Tournament> getTournoi (){
		List<Tournament> listTournoi = serviceTournoi.retrieveAllTournoi();
		return listTournoi ;
	}
	
	@DeleteMapping("/remove-tournoi/{tournoi-id}")
	public void removeComment(@PathVariable("tournoi-id") Long idTournament) {
		serviceTournoi.deleteTournoi(idTournament);
	}
	
	@PutMapping("/modify-tournoi")
	public Tournament updateTournoi(@RequestBody Tournament t) {
		return serviceTournoi.updateTournoi(t);
		
	}
	
	@GetMapping("/Get/TopActivityPerTournament")
	public List<TopActiviteInTournament> topacttourn (){
		return serviceTournoi.topacttourn();
	}
}
