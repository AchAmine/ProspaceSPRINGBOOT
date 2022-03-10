package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceQuestion implements IServiceQuestion {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	QuizzRepository quizzRepository;
	
	@Override
	public Question AddQuestionAndAffectToQuizz(Question question,Long idQuizz) {
		Quizz q = quizzRepository.findById(idQuizz).orElse(null);
		question.setQuiz(q);;
		return questionRepository.save(question);
	}

	@Override
	public List<Question> retrieveQuizzQuestions(Long QuizzId) {
		Quizz quizz = quizzRepository.findById(QuizzId).orElse(null);
	List<Question> questions = questionRepository.findByQuiz(quizz);
		return questions;
	}
	
	@Override
	public void deleteQuestion(Long id) {
		Question Q = questionRepository.findById(id).orElse(null);
		
		// delete question from quizz
		Quizz quizz = Q.getQuiz();
		List<Question> quizQuestions = quizz.getQuestions();
		quizQuestions.remove(Q);
		// 
		
		//questionRepository.deleteById(id);
		questionRepository.delete(Q); 
	}

	@Override
	public Question updateQuestion(Question q, Long QuizzId) {
		Quizz quizz= quizzRepository.findById(QuizzId).orElse(null);
		q.setQuiz(quizz);
		return questionRepository.save(q);
	}
	/*@Override
	public Question AddQuestionAndAffectToQuizz(Question question,Long idQuizz) {
		Quizz q = quizzRepository.findById(idQuizz).orElse(null);
		q.getQuestions().add(question);
		return questionRepository.save(question);
	}*/
	
	

}
