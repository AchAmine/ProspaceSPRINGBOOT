package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Answer;

public interface IServiceAnswer {
	public Answer addAnswerAndAffecttoQuestionAndQuizz(Answer a,Long idQuestion);
	void deleteAnswer(Long id);
	Answer updateAnswer(Answer a);
	//public Answer findAnswerById(Long id);
	public List<Answer> findAnswersByQuestion(Long idQuestion);
	public Answer retrieveAnswer(Long id);


}
