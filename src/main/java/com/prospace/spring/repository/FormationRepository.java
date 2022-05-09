package com.prospace.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{
	@Query("Select f from Formation f where f.isDeleted= 0")
	public List<Formation>  getUndeletedFormations();
	@Query("Select f from Formation f where f.isDeleted= 0 and f.endsAt< ?1")
	public List<Formation>  getUndeletedAndCuurentFormations(Date d);
}
