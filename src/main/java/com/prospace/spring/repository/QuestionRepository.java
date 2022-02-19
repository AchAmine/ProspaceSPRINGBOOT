package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
