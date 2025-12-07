package com.example.jbd.dto.response.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AbstractApiException {

    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid username or password.");
    }
}
