package com.ojodev.cookinghero.recipes.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojodev.cookinghero.recipes.bean.ApiError;
import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.exception.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  {
 
	@Autowired
	private Messages messages;
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException e) {
		return new ResponseEntity<>(new ApiError(e.getCode(),e.getDescription()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException e) {
		return new ResponseEntity<>(new ApiError(e.getCode(),e.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
		return new ResponseEntity<>(new ApiError(messages.get("error.customer.server.code"),messages.get("error.customer.server.desc")), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}