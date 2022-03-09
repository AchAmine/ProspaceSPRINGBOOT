package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
import com.prospace.spring.repository.QuizzRepository;
import com.prospace.spring.repository.ResponseRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceResponse implements IServiceResponse {
	
	@Autowired
	ResponseRepository responseRepository;
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuizzRepository quizzRepository;

	@Override
	@Transactional
	// public Response addResponse(Long idQuestion,Long idAnswer,Long idUser)
	public Response addResponse(Response response ,Long idUser,Long idQuizz) {
	//	Question question = questionRepository.findById(idQuestion).orElse(null);
	//	Answer answer = answerRepository.findById(idAnswer).orElse(null);
		User user = userRepository.findById(idUser).orElse(null);
		Quizz quizz= quizzRepository.findById(idQuizz).orElse(null);
		//Response r = new Response();
	//	r.setQuestion(question);
		
			//List<Answer> la= new ArrayList<Answer>();
		//	la.add(answer);
			
	//	answer.getResponse().add(r);
		//r.getSelectedAnswers().add(answer);
	//	r.setSelectedAnswers(la);
		response.setUserresponse(user);
		response.setQuizz(quizz);
		//answerRepository.saveAll(response.getSelectedAnswers());
		return responseRepository.save(response);
	}

}
