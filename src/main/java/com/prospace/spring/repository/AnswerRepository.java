package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Quizz;
import java.lang.Boolean;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	
}
