package com.revature.controller;

import com.revature.dto.ExceptionMessageDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {

	private ExceptionHandler<DatabaseException> databaseExceptionHandler = (e, ctx) -> {
		ctx.status(500); 	// internal server error

		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};

	private ExceptionHandler<ClientNotFoundException> clientNotFoundExceptionHandler = (e, ctx) -> {
		ctx.status(404); 
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		ctx.status(400); 
		
		ExceptionMessageDTO messageDTO = new ExceptionMessageDTO();
		messageDTO.setMessage(e.getMessage());
		
		ctx.json(messageDTO);
	};
	
	
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(DatabaseException.class, databaseExceptionHandler);
		app.exception(ClientNotFoundException.class, clientNotFoundExceptionHandler);
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		
	}
}

