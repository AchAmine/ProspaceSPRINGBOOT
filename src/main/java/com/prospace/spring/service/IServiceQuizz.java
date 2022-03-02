package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Quizz;

public interface IServiceQuizz {
	public Quizz AddQuizz(Quizz quiz,Long Partnerid);
	List<Quizz> retrieveAllQuizz();

	void deleteQuizz(Long id);
	Quizz updateQuizz(Quizz o);
	public Quizz retrieveQuizz(Long id);
//public Quizz AjouterQuestionEtAffecterAuQuizz(Quizz quizz , Long idQuestion);


}
