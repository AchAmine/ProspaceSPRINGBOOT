package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Formation;

public interface IServiceFormation {
	void addFormation(Formation f,Long idu);
	List<Formation> getFormations();
	List<Formation> getUndeletedFormation();
	void deleteFormation(Long id);
	void updateFormation(Formation f);
	void participerFormation(Long idf,Long idu);
}
