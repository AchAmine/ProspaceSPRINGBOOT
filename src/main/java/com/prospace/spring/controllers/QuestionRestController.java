package com.prospace.spring.controllers;

import java.util.List;

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

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.service.IServiceQuestion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@Api(tags = "Question management")
@RequestMapping("/Quizz/Question")
public class QuestionRestController {
	@Autowired
	IServiceQuestion serviceQuestion;
	
	@ApiOperation(value = "Add question")
	@PostMapping("/add-question/{Quizz-id}")
	public Question addQuestion(@RequestBody Question question,@PathVariable("Quizz-id")Long idQuizz) {
	return serviceQuestion.AddQuestionAndAffectToQuizz(question,idQuizz);
	}
	 @ApiOperation(value = "Questions list")
		@GetMapping("/retrieve-quizz-Questions/{Quizz-id}")
		public List<Question> getQuestion(@PathVariable("Quizz-id")Long QuizzId) {
		List<Question> listQuestion= serviceQuestion.retrieveQuizzQuestions(QuizzId);
		return listQuestion;
		}
	 
		// http://localhost:8089/SpringMVC/Question/remove-Question/{Question-id}
		@ApiOperation(value = "Delete Question")
		@DeleteMapping("/remove-Question/{Question-id}")
		public void removeQuestion(@PathVariable("Question-id") Long QuestionId) {
		serviceQuestion.deleteQuestion(QuestionId);
		}

		// http://localhost:8089/SpringMVC/Question/modify-Question
		@ApiOperation(value = "Update Question")
		@PutMapping("/modify-Question/{Quizz-id}")
		public Question modifyQuestion(@RequestBody Question question,@PathVariable("Quizz-id") Long QuizzId) {
		return serviceQuestion.updateQuestion(question,QuizzId);
		}
		
		
		/*// http://localhost:8089/SpringMVC/Question/modify-Question
		@ApiOperation(value = "Update Question")
		@PutMapping("/modify-Question/{Question-id}/{Quizz-id}")
		public Question modifyQuestion(@RequestBody Question question,@PathVariable("Question-id") Long QuestionId,@PathVariable("Quizz-id") Long QuizzId){
		return serviceQuestion.updateQuestion(question,QuestionId,QuizzId);
		}
*/
		
		// http://localhost:8089/SpringMVC/Quizz/Quizz-Quizz/8
				@ApiOperation(value = "Get one question")
				@GetMapping("/retrieve-question/{question-id}")
				public Question retrieveQuestion(@PathVariable("question-id") Long QuestionId) {
				return serviceQuestion.retrieveQuestion(QuestionId);
		
		
		
}}
