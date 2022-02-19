package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Quizz;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz, Long>{

}
