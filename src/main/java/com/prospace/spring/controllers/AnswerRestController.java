package com.prospace.spring.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.service.IServiceAnswer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@Api(tags = "Answer management")
@RequestMapping("/Quizz/Question/Answers")
public class AnswerRestController {
	@Autowired
	IServiceAnswer serviceAnswer;
	
	@ApiOperation(value = "Add answer to Question and quizz")
	@PostMapping("/add-answer/{Question-id}")
	public Answer addAnswerAndAffecttoQuestionAndQuizz(@RequestBody Answer answer,@PathVariable("Question-id")Long idQuestion)
	{

	return serviceAnswer.addAnswerAndAffecttoQuestionAndQuizz(answer,idQuestion);
	}
	
	// http://localhost:8089/SpringMVC/Answer/remove-Answer/{Answer-id}
			@ApiOperation(value = "Delete Answer")
			@DeleteMapping("/remove-Answer/{Answer-id}")
			public void removeAnswer(@PathVariable("Answer-id") Long AnswerId) {
			serviceAnswer.deleteAnswer(AnswerId);
			}

			// http://localhost:8089/SpringMVC/Answer/modify-Answer
			@ApiOperation(value = "Update Answer")
			@PutMapping("/modify-Answer")
			public Answer modifyAnswer(@RequestBody Answer answer) {
			return serviceAnswer.updateAnswer(answer);
			}
			
			@ApiOperation(value = "Question Answers")
			@GetMapping("/retrieve-Question-Answers/{Question-id}")
			public List<Answer> findAnswersByQuestion(@PathVariable("Question-id") Long idQuestion) {
			return serviceAnswer.findAnswersByQuestion(idQuestion);
			}
			// http://localhost:8089/SpringMVC/Answer/retrieve-answer/8
			@ApiOperation(value = "Get one answer")
			@GetMapping("/retrieve-answer/{answer-id}")
			public Answer retrieveOneAnswer(@PathVariable("answer-id") Long answerId) {
			return serviceAnswer.retrieveAnswer(answerId);
		

			

}}
