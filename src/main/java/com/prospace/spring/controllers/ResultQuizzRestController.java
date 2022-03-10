package com.prospace.spring.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;
import com.prospace.spring.service.IServiceResultQuizz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Score ")
@RequestMapping("/Quizz/Score")
public class ResultQuizzRestController {
	@Autowired
	IServiceResultQuizz serviceResultQuizz;
	
	@ApiOperation(value = "Score Quizz")
	@PostMapping("/{Quizz-id}/{User-id}")
	public ResultQuizz ScoreQuizz(@PathVariable("Quizz-id")Long quizzId,@PathVariable("User-id")Long userId){
		return serviceResultQuizz.calculScore(quizzId,userId);
	}
	@ApiOperation(value = "TOP3 Score Quizz")
	@GetMapping("/TOP3/{Quizz-id}")
	public HashMap<String, Float> TOP3(@PathVariable("Quizz-id")Long quizzId) {
		
		return serviceResultQuizz.TOP3(quizzId);
	}

}
