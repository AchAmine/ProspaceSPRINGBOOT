package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;

import lombok.extern.slf4j.Slf4j;

@Service
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
		questionRepository.deleteById(id);
		
	}

	@Override
	public Question updateQuestion(Question q) {
		return questionRepository.save(q);
	}
	/*@Override
	public Question AddQuestionAndAffectToQuizz(Question question,Long idQuizz) {
		Quizz q = quizzRepository.findById(idQuizz).orElse(null);
		q.getQuestions().add(question);
		return questionRepository.save(question);
	}*/
	
	

}
