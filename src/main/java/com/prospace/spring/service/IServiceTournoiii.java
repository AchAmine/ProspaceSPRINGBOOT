package com.prospace.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prospace.spring.entity.TopActiviteInTournament;
import com.prospace.spring.entity.Tournament;

@Service
public interface IServiceTournoiii {

	 Tournament  addTournoi( Tournament  t,Long idActivity );
	void deleteTournoi(Long idTournament);
	Tournament updateTournoi(Tournament t);
	List<Tournament> retrieveAllTournoi();
	public 	List<TopActiviteInTournament>topacttourn();

}
