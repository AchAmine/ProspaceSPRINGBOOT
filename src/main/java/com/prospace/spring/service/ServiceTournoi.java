package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Activity;
import com.prospace.spring.entity.TopActiviteInTournament;
import com.prospace.spring.entity.Tournament;
import com.prospace.spring.repository.ActivityRepository;
import com.prospace.spring.repository.TournamentRepository;

@Service
public class ServiceTournoi implements IServiceTournoiii{

	@Autowired
	TournamentRepository tournoiRepository ;
	
	@Autowired
	ActivityRepository activityRepository ;
	
	@Override
	public Tournament addTournoi(Tournament tournoi, Long idActivity) {
		Activity a = activityRepository.findById(idActivity).orElse(null);
        tournoi.setActivity(a);
        
        return tournoiRepository.save(tournoi);
	}


	@Override
	public Tournament updateTournoi(Tournament t) {
		return tournoiRepository.save(t);
	}

	@Override
	public List<Tournament> retrieveAllTournoi() {
		return tournoiRepository.findAll();
	}


	@Override
	public void deleteTournoi(Long idTournament) {
		
		Tournament tournoi = tournoiRepository.findById(idTournament).orElse(null);
		 tournoiRepository.delete(tournoi);
	}


	@Override
	public List<TopActiviteInTournament> topacttourn() {
		return tournoiRepository.topacttourn();
	}

}
