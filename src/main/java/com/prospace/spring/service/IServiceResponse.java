package com.prospace.spring.service;

import com.prospace.spring.entity.Response;

public interface IServiceResponse {
	//public Response addResponse(Long idQuestion ,Long idAnswer,Long idUser);
	public Response addResponse(Response response ,Long idUser,Long idQuizz);
}
