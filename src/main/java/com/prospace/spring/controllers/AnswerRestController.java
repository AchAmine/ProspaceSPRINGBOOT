package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.service.IServiceAnswer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Answer management")
@RequestMapping("/Answer")
public class AnswerRestController {
	@Autowired
	IServiceAnswer serviceAnswer;
	
	@ApiOperation(value = "Add answer")
	@PostMapping("/add-answer/{Question-id}")
	public Answer addAnswerAndAffecttoQuestion(@RequestBody Answer answer,@PathVariable("Question-id")Long idQuestion)
	{

	return serviceAnswer.addAnswerAndAffecttoQuestion(answer,idQuestion);
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

}
