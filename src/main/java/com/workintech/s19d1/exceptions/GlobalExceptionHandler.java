package com.workintech.s19d1.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ApiException exception) {
        ExceptionResponse errorResponse = new ExceptionResponse(exception.getMessage(), exception.getHttpStatus().value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exception) {
        ExceptionResponse errorResponse = new ExceptionResponse(exception.getMessage(), org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}