package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Quizz;
import com.prospace.spring.service.IServiceQuizz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Quizz management")
@RequestMapping("/Quizz")
public class QuizzRestController {
	 @Autowired
	 IServiceQuizz serviceQuizz;
	
	 @ApiOperation(value = "Add Quizz")
		@PostMapping("/add-quizz/{Partner-id}")
		public Quizz addQuizz(@RequestBody Quizz q,@PathVariable("Partner-id")Long Partnerid) {
		return serviceQuizz.AddQuizz(q,Partnerid);
		}
	 
	 @ApiOperation(value = "Quizz list")
		@GetMapping("/retrieve-all-Quizz")
		public List<Quizz> getQuizz() {
		List<Quizz> listQuizz= serviceQuizz.retrieveAllQuizz();
		return listQuizz;
		}
	 
		// http://localhost:8089/SpringMVC/Quizz/remove-Quizz/{Quizz-id}
		@ApiOperation(value = "Delete Quizz")
		@DeleteMapping("/remove-Quizz/{Quizz-id}")
		public void removeQuizz(@PathVariable("Quizz-id") Long QuizzId) {
		serviceQuizz.deleteQuizz(QuizzId);
		}
		
		// http://localhost:8089/SpringMVC/Quizz/modify-Quizz
		@ApiOperation(value = "Update Quizz")
		@PutMapping("/modify-quizz")
		public Quizz modifyQuizz(@RequestBody Quizz quizz) {
		return serviceQuizz.updateQuizz(quizz);
		}
		
			/*	@ApiOperation(value = "Affecter Question quizz")
				@PutMapping("/Affecter-question-quizz")
				public Quizz AjouterQuestionEtAffecterAuQuizz(@RequestBody Quizz quizz ,@PathVariable("Question-id") Long idQuestion){
				return serviceQuizz.AjouterQuestionEtAffecterAuQuizz(quizz, idQuestion);
				}*/		

		// http://localhost:8089/SpringMVC/Quizz/Quizz-Quizz/8
		@ApiOperation(value = "Get one quizz")
		@GetMapping("/retrieve-quizz/{quizz-id}")
		public Quizz retrieveQUIZZ(@PathVariable("quizz-id") Long quizzId) {
		return serviceQuizz.retrieveQuizz(quizzId);
		
	 

}
}