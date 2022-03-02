package com.prospace.spring.service;

import com.prospace.spring.entity.Answer;

public interface IServiceAnswer {
	public Answer addAnswerAndAffecttoQuestionAndQuizz(Answer a,Long idQuestion,Long idQuizz);
	void deleteAnswer(Long id);
	Answer updateAnswer(Answer a);

}
