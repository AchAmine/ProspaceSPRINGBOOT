package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;
import com.prospace.spring.repository.ResponseRepository;
import com.prospace.spring.repository.ResultQuizzRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ServiceResultQuizz implements IServiceResultQuizz {
	
	@Autowired
	QuizzRepository quizzRepository;
	
	@Autowired
	ResultQuizzRepository resultQuizzRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	ResponseRepository responseRepository;
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public ResultQuizz calculScore(Long quizzId,Long userId) {
		float score=0;
		int nbFalseQuestions=0,nbTrueQuestions =0;
		Quizz quizz = quizzRepository.findById(quizzId).orElse(null);
		User user= userRepository.findById(userId).orElse(null);
		
		
			for(Response r : responseRepository.findByUserresponse(user))
		{
				for(Question q : questionRepository.findByQuiz(quizz)){
				if(r.getSelectedAnswer()==q.getCorrectAnswer()){
					score++;
					nbTrueQuestions++;
				}else {
					nbFalseQuestions++;
				}
					
			}}
		 score = (nbTrueQuestions/ questionRepository.NbQuestions(quizz))*100;
			// score=(noteIndiv/responseRepository.NbResponsesQuizz())*100;
		log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+responseRepository.NbResponsesQuizz());
		ResultQuizz result= new ResultQuizz(score,questionRepository.NbQuestions(quizz),
				nbTrueQuestions,nbFalseQuestions,user,quizz);

		return resultQuizzRepository.save(result);
	}

}
