package com.ojodev.cookinghero.recipes.api.handler;

import com.ojodev.cookinghero.recipes.api.model.ApiError;
import com.ojodev.cookinghero.recipes.api.model.ApiFieldError;
import com.ojodev.cookinghero.recipes.api.model.ApiFieldsError;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

    private static final String FIELD_ERROR_SEPARATOR = ". ";

    @Autowired
    private Messages messages;

    @ExceptionHandler(ApiBadRequestException.class)
    public ResponseEntity<ApiError> handleApiBadRequestException(ApiBadRequestException e) {
        LOGGER.error("ApiBadRequestException: " + e);
        return new ResponseEntity<>(new ApiError(
                isEmpty(e.getCode()) ? messages.get("error.badrequest.code") : e.getCode(),
                isEmpty(e.getDescription()) ? messages.get("error.badrequest.desc") : e.getDescription()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiFieldsException.class)
    public ResponseEntity<ApiError> handleApiFieldsException(ApiFieldsException e) {
        LOGGER.error("ApiFieldsException: " + e);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiFieldsError(
                        messages.get("error.badrequest.invalidparams.code"),
                        messages.get("error.badrequest.invalidparams.desc"), e.getFields().isEmpty() ? new ArrayList<>() :
                        e.getFields().stream().map(field -> new ApiFieldError(field.getCode(), field.getField(), field.getDescription())).collect(Collectors.toList())));
    }

    @ExceptionHandler(ApiAcceptException.class)
    public ResponseEntity<ApiError> handleApiAcceptException(ApiAcceptException e) {
        LOGGER.error("ApiAcceptException: " + e);
        return new ResponseEntity<>(new ApiError(
                isEmpty(e.getCode()) ? messages.get("error.notacceptable.code") : e.getCode(),
                isEmpty(e.getDescription()) ? messages.get("error.notacceptable.desc") : e.getDescription()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException e) {
        LOGGER.info("NotFoundException: " + e);
        return new ResponseEntity<>(new ApiError(
                isEmpty(e.getCode()) ? messages.get("error.notfound.code") : e.getCode(),
                isEmpty(e.getDescription()) ? messages.get("error.notfound.desc") : e.getDescription()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException e) {
        LOGGER.error("ApiException: " + e);
        return new ResponseEntity<>(new ApiError(
                isEmpty(e.getCode()) ? messages.get("error.server.code") : e.getCode(),
                isEmpty(e.getDescription()) ? messages.get("error.server.desc") : e.getDescription()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodNotValidException(MethodArgumentNotValidException e) {
        LOGGER.info("MethodArgumentNotValidException: " + e);
        StringBuilder errors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(FIELD_ERROR_SEPARATOR);
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.append(error.getObjectName()).append(" ").append(error.getDefaultMessage()).append(FIELD_ERROR_SEPARATOR);
        }
        return new ResponseEntity<>(new ApiError(messages.get("error.badrequest.code"), errors.toString().trim()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<ApiFieldsError> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        LOGGER.info("MissingRequestHeaderException: " + e);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
             //   .headers(genereateResponseHeaders(headers))
                .body(new ApiFieldsError(
                        messages.get("error.badrequest.invalidparams.code"),
                        messages.get("error.badrequest.invalidparams.desc"),
                        Arrays.asList(new ApiFieldError(
                                messages.get("error.badrequest.invalidparams.fields.headerparamrequired.code"),
                                e.getHeaderName(),
                                e.getHeaderName() + " " + messages.get("error.badrequest.invalidparams.fields.headerparamrequired.desc")))));
        
    }

    private HttpHeaders genereateResponseHeaders(HttpHeaders headers) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Accept-Language", headers.getAcceptLanguageAsLocales().get(0).getLanguage());
        return responseHeaders;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ApiError> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        LOGGER.info("MissingServletRequestParameterException: " + e);
        return new ResponseEntity<>(new ApiError(e.getLocalizedMessage(), e.getParameterName() + " " + messages.get("error.badrequest.parammissing")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.info("HttpMessageNotReadableException: " + e);
        return new ResponseEntity<>(new ApiError(messages.get("error.badrequest.code"), messages.get("error.badrequest.malformed")), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        LOGGER.error("Exception: " + e);
        return new ResponseEntity<>(new ApiError(messages.get("error.server.code"), messages.get("error.server.desc")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}