package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Section;

public interface IServiceSection {

	List<Section> retrieveAllSections();

	Section retrieveSection(Long id);


	Section addSection(Section s);

	Section updateSection(Section s);

	void deleteSection(Long id);
	
	
	List<Section> findByName(String name);

}
