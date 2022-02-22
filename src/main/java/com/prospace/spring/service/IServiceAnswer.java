package com.prospace.spring.service;

import com.prospace.spring.entity.Answer;

public interface IServiceAnswer {
	public Answer addAnswerAndAffecttoQuestion(Answer a,Long idQuestion);
	void deleteAnswer(Long id);
	Answer updateAnswer(Answer a);

}
