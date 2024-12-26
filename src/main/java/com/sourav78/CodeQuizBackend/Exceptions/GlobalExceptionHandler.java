package com.sourav78.CodeQuizBackend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom default exceptions
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleDefaultException(Exception exception){

        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                exception.getMessage() == null ? "Internal server error" : exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
