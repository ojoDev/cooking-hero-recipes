package com.ojodev.cookinghero.recipes.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ojodev.cookinghero.recipes.bean.ApiError;
import com.ojodev.cookinghero.recipes.bean.ApiException;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.exception.NotFoundException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler  {
 
	private static final String FIELD_ERROR_SEPARATOR = ". ";
	@Autowired
	private Messages messages;
	
//	@ExceptionHandler(NotFoundException.class)
//	public ResponseEntity<ApiError> handleCustomAPIException(NotFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//	   return new ResponseEntity<>(new ApiError(e.getCode(),e.getDescription()), HttpStatus.NOT_FOUND);
//	 }
	
	// TODO DMS Dont work
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException e) {
		return new ResponseEntity<>(new ApiError(e.getCode(),e.getDescription()), HttpStatus.NOT_FOUND);
    }
	
//	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
//	  MethodArgumentTypeMismatchException ex, WebRequest request) {
//	    String error = 
//	      ex.getName() + " should be of type " + ex.getRequiredType().getName();
//	 
//	    ApiError apiError = 
//	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
//	    return new ResponseEntity<Object>(
//	      apiError, new HttpHeaders(), apiError.getStatus());
//	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	protected ResponseEntity<ApiError> handleMethodArgumentNotValid(
//	  MethodArgumentNotValidException ex, 
//	  HttpHeaders headers, 
//	  HttpStatus status, 
//	  WebRequest request) {
//	    List<String> errors = new ArrayList<String>();
//	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//	        errors.add(error.getField() + ": " + error.getDefaultMessage());
//	    }
//	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//	    }
//	     //TODO DMS: Habría que hacer un nuevo tipo de error que pueda tener fields, o pintar todos los errores, ahora solo coje errors.get(0)
//		return new ResponseEntity<>(new ApiError(ex.getLocalizedMessage(), errors.get(0)), HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodNotValidException(MethodArgumentNotValidException e) {
		StringBuilder errors = new StringBuilder();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
	        errors.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(FIELD_ERROR_SEPARATOR);
	    }
	    for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
	        errors.append(error.getObjectName()).append(" ").append(error.getDefaultMessage()).append(FIELD_ERROR_SEPARATOR);
	    }
		return new ResponseEntity<>(new ApiError(messages.get("error.badrequest.code"),errors.toString().trim()), HttpStatus.BAD_REQUEST);
    }
    
	@ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleServletRequestParameterException(MissingServletRequestParameterException e) {
		return new ResponseEntity<>(new ApiError(e.getLocalizedMessage(),e.getParameterName()+" "+messages.get("error.badrequest.parammissing")), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException e) {
		return new ResponseEntity<>(new ApiError(messages.get("error.server.code"),e.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception e) {
	 return new ResponseEntity<>(new ApiError(messages.get("error.server.code"),messages.get("error.server.desc")), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}