package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Response;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.AnswerRepository;
import com.prospace.spring.repository.QuestionRepository;
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

	@Override
	public Response addResponse(Long idQuestion,Long idAnswer,Long idUser) {
		Question question = questionRepository.findById(idQuestion).orElse(null);
		Answer answer = answerRepository.findById(idAnswer).orElse(null);
		User user = userRepository.findById(idUser).orElse(null);
Response r = new Response();
		r.setQuestion(question);
		r.setSelectedAnswer(answer);
		r.setUserresponse(user);
		return responseRepository.save(r);
	}

}
