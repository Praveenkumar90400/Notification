package com.gts.notification.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gts.notification.model.response.MethodUtils;


@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value=ApplicationException.class)
	public ResponseEntity<String> applicationException(ApplicationException exception){
		HttpStatus status=HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
	}
	
	@ExceptionHandler(value=IDNotFoundException.class)
	public ResponseEntity<String> IDNotFoundException(IDNotFoundException exception){
		HttpStatus status=HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
	}
	
	@ExceptionHandler(value=DuplicationException.class)
	public ResponseEntity<String> duplicationException(DuplicationException exception){
		HttpStatus status=HttpStatus.FORBIDDEN;
		return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
	}
}
