package com.example.rest.service.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectContactNameFilterException extends RuntimeException {
    public IncorrectContactNameFilterException(String message) {
        super(message);
    }
}
