package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
@Service
public class ServiceAnswer implements IServiceAnswer {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	
	public Answer addAnswerAndAffecttoQuestion(Answer a,Long idQuestion){
		Question q = questionRepository.findById(idQuestion).orElse(null);
		q.getAnswers().add(a);
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
