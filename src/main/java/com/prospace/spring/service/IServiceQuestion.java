package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;

public interface IServiceQuestion {
	public Question AddQuestionAndAffectToQuizz(Question question,Long idQuizz);
	List<Question> retrieveQuizzQuestions(Long QuizzId);

	void deleteQuestion(Long id);
	public Question updateQuestion(Question q);
//	public Question updateQuestion(Question q,Long QuestionId,Long QuizzId);


}
