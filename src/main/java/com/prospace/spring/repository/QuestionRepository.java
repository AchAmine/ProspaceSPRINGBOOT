package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findByQuiz(Quizz quiz);
}
