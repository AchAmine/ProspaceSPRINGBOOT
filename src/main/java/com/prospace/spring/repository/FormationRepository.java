package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

}
