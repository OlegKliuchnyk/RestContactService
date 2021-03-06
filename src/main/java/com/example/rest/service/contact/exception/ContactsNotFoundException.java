package com.example.rest.service.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ContactsNotFoundException extends BaseException {
    public ContactsNotFoundException(String message) {
        super(message, HttpStatus.NO_CONTENT);
    }
}
