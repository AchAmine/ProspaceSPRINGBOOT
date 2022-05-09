package com.prospace.spring.service;

import java.util.HashMap;
import java.util.List;

import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;

public interface IServiceResultQuizz {

	
	public ResultQuizz calculScore(Long quizzId,Long userId);
	public List<ResultQuizz> retrieveAllResults();
	HashMap<String, Float> TOP3(Long idquizz);
	boolean userResult(Long idquizz , Long idUser);

}
