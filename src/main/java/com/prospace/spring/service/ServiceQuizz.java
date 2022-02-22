package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;
import com.prospace.spring.repository.UserRepository;



@Service
public class ServiceQuizz implements IServiceQuizz{
	
	@Autowired
	QuizzRepository quizzRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuestionRepository questionRepository;

	@Override
	 public Quizz AddQuizz(Quizz quizz,Long Partnerid) {
		User partner = userRepository.findById(Partnerid).orElse(null);
		quizz.setPartner(partner);
		return quizzRepository.save(quizz);
	}
	
/*	@Override
	public Quizz AjouterQuestionEtAffecterAuQuizz(Quizz quizz , Long idQuestion)
		{
		Question q = questionRepository.findById(idQuestion).orElse(null);
		quizz.getQuestions().add(q);
		quizzRepository.save(quizz);
		return quizz;
		
		
		
		
	}*/

	@Override
	public List<Quizz> retrieveAllQuizz() {
		return quizzRepository.findAll();
	}

	@Override
	public void deleteQuizz(Long id) {
		quizzRepository.deleteById(id);
	}

	@Override
	public Quizz updateQuizz(Quizz q) {
		return quizzRepository.save(q);
	}

	@Override
	public Quizz retrieveQuizz(Long id) {
		return quizzRepository.findById(id).orElse(null);
	}

}
