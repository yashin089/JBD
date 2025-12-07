package com.example.jbd.dto.response.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApiException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Entity Not Found");
    }
}
