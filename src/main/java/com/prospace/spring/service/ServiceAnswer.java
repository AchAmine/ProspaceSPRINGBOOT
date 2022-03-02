package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;
@Service
public class ServiceAnswer implements IServiceAnswer {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	QuizzRepository quizzRepository;
	
	
	public Answer addAnswerAndAffecttoQuestionAndQuizz(Answer a,Long idQuestion,Long idQuizz){
		Question q = questionRepository.findById(idQuestion).orElse(null);
		Quizz quizz = quizzRepository.findById(idQuizz).orElse(null);
		q.getAnswers().add(a);
		quizz.getQuestions().add(q);
		answerRepository.save(a);
		return a;
	}

	@Override
	public void deleteAnswer(Long id) {
		answerRepository.deleteById(id);
		
	}

	@Override
	public Answer updateAnswer(Answer a) {
		return answerRepository.save(a);
	}
	
}
