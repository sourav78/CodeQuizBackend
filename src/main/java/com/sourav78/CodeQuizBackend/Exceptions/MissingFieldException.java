package com.sourav78.CodeQuizBackend.Exceptions;

public class MissingFieldException extends RuntimeException{
    public MissingFieldException() {
    }

    public MissingFieldException(String message) {
        super(message);
    }
}
