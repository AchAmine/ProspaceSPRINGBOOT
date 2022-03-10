package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospace.spring.entity.Vote;

public interface VoteRepository  extends JpaRepository<Vote, Integer>{

}
