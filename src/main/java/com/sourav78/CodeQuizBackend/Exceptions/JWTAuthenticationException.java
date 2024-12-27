package com.sourav78.CodeQuizBackend.Exceptions;

public class JWTAuthenticationException extends RuntimeException{
    public JWTAuthenticationException() {
    }

    public JWTAuthenticationException(String message) {
        super(message);
    }
}
