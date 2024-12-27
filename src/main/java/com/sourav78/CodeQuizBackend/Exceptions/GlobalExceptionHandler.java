package com.sourav78.CodeQuizBackend.Exceptions;

import io.jsonwebtoken.JwtException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    //Handle Resource already exist exception
    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException resourceAlreadyExistException){
        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                resourceAlreadyExistException.getMessage(),
                HttpStatus.CONFLICT
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.CONFLICT);
    }

    //Handle Resource not found exception
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                resourceNotFoundException.getMessage(),
                HttpStatus.CONFLICT
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.CONFLICT);
    }

    //Handle missing field exception
    @ExceptionHandler(value = {MissingFieldException.class})
    public ResponseEntity<Object> handleMissingFieldException(MissingFieldException missingFieldException){
        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                missingFieldException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.BAD_REQUEST);
    }

    // Handle JWT exception
    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<Object> handleJWTException(JwtException jwtException) {
        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                jwtException.getMessage(),
                HttpStatus.UNAUTHORIZED
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.UNAUTHORIZED);
    }

    // Handle Auth exceptions
    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleBadCredentialException(AuthenticationException exception){

        // Create exception object
        CodeQuizException codeQuizException = new CodeQuizException(
                "Invalid credentials. Please check your credentials",
                HttpStatus.UNAUTHORIZED
        );

        return new ResponseEntity<>(codeQuizException, HttpStatus.UNAUTHORIZED);
    }

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
