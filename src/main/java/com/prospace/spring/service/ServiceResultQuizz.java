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
		float score=0; // score = nbre total de points
		
		int nbFalseAnswers=0,nbTrueAnswers =0;
		Quizz quizz = quizzRepository.findById(quizzId).orElse(null);
		User user= userRepository.findById(userId).orElse(null);
		//---------------------------------------------------
			for(Response r : responseRepository.userResponses(quizzId,userId))
						{	
					for(Answer a : r.getSelectedAnswers()){
					
				if(a.getIsCorrect()==true){
				score+=(a.getNbreptsanswer());	
					nbTrueAnswers++;
				}else if(a.getIsCorrect()==false){
					score-=(a.getNbreptsanswer());
					nbFalseAnswers++;
				}
					}	
	
			// score=(noteIndiv/responseRepository.NbResponsesQuizz())*100;
					
			}
			//---------------------------------------------------
			
			float scoreFinal =0 ; // score final du quizz en % 
			float totalPts = totalPts(quizz);
			scoreFinal = (score/totalPts)*100;
			ResultQuizz result= new ResultQuizz();
			
			result.setScore(scoreFinal);
			result.setCorrectAnswers(nbTrueAnswers);
			result.setFalseAnswers(nbFalseAnswers);
			result.setNbreQuestions(questionRepository.NbQuestions(quizz));
			result.setUser(user);
			result.setQuizz(quizz);
			return resultQuizzRepository.save(result);
			
	}
	
	// fonction calcul nombre total des points de correctAnswer
	public float totalPts(Quizz quiz) {
		float total = 0;
		for(Question question : quiz.getQuestions()){
			for (Answer answer : question.getAnswers()){
				if (answer.getIsCorrect()==true) {
					total = total + answer.getNbreptsanswer();
				}
			}
		}
		return total;
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
	@Override
	public ResultQuizz userResult(Long idquizz , Long idUser)
	{
		Quizz quizz = quizzRepository.findById(idquizz).orElse(null);
		User user = userRepository.findById(idUser).orElse(null);
		if(resultQuizzRepository.getResultbyquizzanduser(idquizz, idUser)!=null){
		return resultQuizzRepository.getResultbyquizzanduser(idquizz, idUser);
			
		}
		return null;
	}
	@Override
	public boolean userResultExits(Long idquizz , Long idUser)
	{
		Quizz quizz = quizzRepository.findById(idquizz).orElse(null);
		User user = userRepository.findById(idUser).orElse(null);
		if(resultQuizzRepository.getResultbyquizzanduser(idquizz, idUser)!=null){
		return true;
			
		}
		return false;
	}
	

}
