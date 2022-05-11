package com.prospace.spring.service;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prospace.spring.entity.Formation;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.FormationRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceFormation implements IServiceFormation {
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	UserRepository userRepository;
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date date;
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

	@Override
	public List<Formation> getUndeletedAndCurrentFormation() {
		List<Formation> f;
		List<Formation> f2=new ArrayList<Formation>();
		
		date= new Date(System.currentTimeMillis());
		log.info("date---------->"+date);
		f=formationRepository.getUndeletedFormations();
		for(Formation fr:f) {
			Period period = Period.between(date
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(),
                    fr.getEndsAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
			log.info(period.toString());
			if((period.getDays())>1) {
				f2.add(fr);
			}
		}
		log.info("period"+f2.toString() );
		return f2;
	}
	}


