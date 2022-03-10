package com.prospace.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Section;
import com.prospace.spring.repository.SectionRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceSection implements IServiceSection {
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Section> retrieveAllSections() {
		// TODO Auto-generated method stub
		return (List<Section>) sectionRepository.findAll();
	}

	@Override
	public Section retrieveSection(Long id) {
		Section section = sectionRepository.findById(id).orElse(null);
		log.info("!!!!!!!!!!!!!!!!!" + section.getIdSection());
		return section;
	}

	

	@Override
	public Section addSection(Section s) {
		// TODO Auto-generated method stub
		return sectionRepository.save(s);
	}

	@Override
	public Section updateSection(Section s) {
		// TODO Auto-generated method stub
		return sectionRepository.save(s);
	}

	@Override
	public void deleteSection(Long id) {
		sectionRepository.deleteById(id);
	}

	@Override
	public List<Section> findByName(String name) {
		return (List<Section>) sectionRepository.findByName(name);
		}
}
