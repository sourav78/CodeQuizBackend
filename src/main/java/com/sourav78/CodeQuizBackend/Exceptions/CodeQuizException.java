package com.sourav78.CodeQuizBackend.Exceptions;

import org.springframework.http.HttpStatus;

// Custom exception class
public class CodeQuizException {

    private final boolean success = false;
    private String message;
    private HttpStatus httpStatus;

    public CodeQuizException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
