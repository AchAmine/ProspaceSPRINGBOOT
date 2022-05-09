package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceAnswer implements IServiceAnswer {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	QuizzRepository quizzRepository;

	
	
	public Answer addAnswerAndAffecttoQuestionAndQuizz(Answer a,Long idQuestion){
		Question q = questionRepository.findById(idQuestion).orElse(null);
		q.getAnswers().add(a);
	//	q.setQuestionpts(questionRepository.NbPtsQuestion(idQuestion));
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
	
	@Override
	
	public List<Answer> findAnswersByQuestion(Long idQuestion) {
		Question q= questionRepository.findById(idQuestion).orElse(null);
		List<Answer> al= new ArrayList<Answer>();
		for (Answer a : q.getAnswers()){
			al.add(a);
		}
		return al;
		
	
	}

	@Override
	public Answer retrieveAnswer(Long id) {
		return answerRepository.findById(id).orElse(null);
	}

	
	
	
	
	}

	
		
	

