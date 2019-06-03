package com.example.rest.service.contact.model;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ErrorResponse {
    private HttpStatus status;
    private String errorMessage;
}
