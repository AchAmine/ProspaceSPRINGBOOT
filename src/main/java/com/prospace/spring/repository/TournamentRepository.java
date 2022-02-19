package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long>{

}
