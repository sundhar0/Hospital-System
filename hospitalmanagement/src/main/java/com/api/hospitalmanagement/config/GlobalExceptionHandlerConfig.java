package com.api.hospitalmanagement.config;

import org.springframework.http.HttpStatusCode;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.hospitalmanagement.exception.InvalidIDException;
import com.api.hospitalmanagement.exception.InvalidUserNameException;


@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

	@ExceptionHandler(InvalidUserNameException.class)
	public ErrorResponse invalidUsernameExceptionHandler(InvalidUserNameException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse invalidIDExceptionHandler(InvalidIDException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400),e.getMessage());
	}
	
}
