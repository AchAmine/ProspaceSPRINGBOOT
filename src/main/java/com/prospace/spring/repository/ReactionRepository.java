package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{

}
