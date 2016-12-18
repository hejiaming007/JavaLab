package com.jimmy.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public String handleBaseException(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = NoHandlerFoundException.class)
	public String handleException(NoHandlerFoundException e) {
		//new ResponseEntity<>(body, headers, HttpStatus.valueOf(404));
		return e.getMessage();
	}
}
