package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findByQuiz(Quizz quiz);
	List<Question> findByResponse(Response r);
	@Query("SELECT count(*) FROM Question q WHERE q.quiz= :quizz")
	int NbQuestions(@Param("quizz") Quizz quizz );
	
}
