package com.prospace.spring.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="http://localhost:4200")
@RestController
@Api(tags = "Score ")
@RequestMapping("/Quizz/Score")
public class ResultQuizzRestController {
	@Autowired
	IServiceResultQuizz serviceResultQuizz;
	
	@ApiOperation(value = "Score Quizz")
	@GetMapping("/{Quizz-id}/{User-id}")
	public ResultQuizz calculScore(@PathVariable("Quizz-id")Long quizzId,@PathVariable("User-id")Long userId){
		return serviceResultQuizz.calculScore(quizzId,userId);
	}
	@ApiOperation(value = "TOP3 Score Quizz")
	@GetMapping("/TOP3/{Quizz-id}")
	public HashMap<String, Float> TOP3(@PathVariable("Quizz-id")Long quizzId) {
		
		return serviceResultQuizz.TOP3(quizzId);
	}
	
	@ApiOperation(value = "user Score Quizz")
	@GetMapping("/user-result/{Quizz-id}/{User-id}")
	public boolean userResult(@PathVariable("Quizz-id")Long quizzId,@PathVariable("User-id")Long userId){
		return serviceResultQuizz.userResult(userId,quizzId);

}
	
	@ApiOperation(value = "find all results")
@GetMapping("/getAll-results")
	public List<ResultQuizz> retrieveAllResults() {
		return serviceResultQuizz.retrieveAllResults();
	}


}
