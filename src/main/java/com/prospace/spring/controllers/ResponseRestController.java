package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Answer;
import com.prospace.spring.entity.Response;
import com.prospace.spring.service.IServiceResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Response management")
@RequestMapping("/Response")
public class ResponseRestController {
	
	@Autowired
	IServiceResponse serviceResponse;
	
	/*@ApiOperation(value = "Add response to Question and quizz")
	@PostMapping("/add-response/{Answer-id}/{Question-id}/{User-id}")
	public Response addResponse(@PathVariable("Question-id")Long idQuestion,@PathVariable("Answer-id")Long idAnswer,@PathVariable("User-id")Long idUser)
	{

	return serviceResponse.addResponse(idQuestion,idAnswer,idUser);
	} */
	

	@ApiOperation(value = "Add response to Question and quizz")
	@PostMapping("/add-response/{Quizz-id}/{User-id}")
	public Response addResponse(@RequestBody Response response ,@PathVariable("User-id")Long idUser,@PathVariable("Quizz-id")Long idQuizz)
	{

	return serviceResponse.addResponse(response,idUser,idQuizz);
	}

}
