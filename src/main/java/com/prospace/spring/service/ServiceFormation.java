package com.prospace.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Formation;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.FormationRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceFormation implements IServiceFormation {
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void addFormation(Formation f,Long idu) {
		Date date = new Date(System.currentTimeMillis());
		f.setCreatedAt(date);
		f.setOrganizer(userRepository.findById(idu).orElse(null));
		formationRepository.save(f);

	}

	@Override
	public List<Formation> getFormations() {
		
		return formationRepository.findAll();
	}

	@Override
	public List<Formation> getUndeletedFormation() {
		
		return formationRepository.getUndeletedFormations();
	}

	@Override
	public void deleteFormation(Long id) {
		Date date = new Date(System.currentTimeMillis());
		Formation deletedFormation = formationRepository.findById(id).orElse(null);
		deletedFormation.setDeleted(true);
		deletedFormation.setDeletedAt(date);
		formationRepository.save(deletedFormation);

	}

	@Override
	public void updateFormation(Formation f) {
		Date date = new Date(System.currentTimeMillis());
		f.setModifiedAt(date);
		formationRepository.save(f);

	}

	@Override
	public void participerFormation(Long idf, Long idu) {
		User u = userRepository.findById(idu).orElse(null);
		Formation f = formationRepository.findById(idf).orElse(null);
		f.getParticipants().add(u);
		formationRepository.save(f);

	}

}
