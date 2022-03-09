package com.prospace.spring.service;

import java.util.HashMap;
import java.util.List;

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
		int nbFalseAnswers=0,nbTrueAnswers =0;
		Quizz quizz = quizzRepository.findById(quizzId).orElse(null);
		User user= userRepository.findById(userId).orElse(null);

			for(Response r : responseRepository.userResponses(quizzId,userId))
		{	
			//	for(Question q :quizz.getQuestions()){
					for(Answer a : r.getSelectedAnswers()){
				if(a.getIsCorrect()==true){
					
				
				score+=(a.getNbreptsanswer());	
					nbTrueAnswers++;
				}else if(a.getIsCorrect()==false){
					score-=(a.getNbreptsanswer());
					nbFalseAnswers++;
				}
					
			}}
			//}
		// score = (nbTrueQuestions/(questionRepository.NbQuestions(quizz)))*100;
			// score=(noteIndiv/responseRepository.NbResponsesQuizz())*100;
		ResultQuizz result= new ResultQuizz(score,nbTrueAnswers,nbFalseAnswers,
				questionRepository.NbQuestions(quizz),user,quizz);

		return resultQuizzRepository.save(result);
	}
	
	@Override
	public List<ResultQuizz> retrieveAllResults() {
		return resultQuizzRepository.findAll();
	}
	
	public HashMap<String, Float> SortByResult(Long idQuizz) {
	Quizz quizz=quizzRepository.findById(idQuizz).orElse(null);
		HashMap<String, Float> hMap = new HashMap<String, Float>();
		List<Object[]> listResult = resultQuizzRepository.TOP3Result(idQuizz);
		for (Object[] obj : listResult) {
		hMap.put( (String)obj[0] , (Float)obj[1]);	
		}  
		return hMap;
	}
	
	@Override
	public HashMap<String, Float> TOP3(Long idquizz) {
		HashMap<String, Float> resulthMap = SortByResult(idquizz);
		//return resultQuizzRepository.TOP3Result();
		return resulthMap;
	}
	
	

}
