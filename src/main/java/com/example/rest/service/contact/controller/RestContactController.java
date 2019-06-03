package com.example.rest.service.contact.controller;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.exception.ContactsNotFoundException;
import com.example.rest.service.contact.exception.IncorrectContactNameFilterException;
import com.example.rest.service.contact.model.CustomPageImpl;
import com.example.rest.service.contact.model.ErrorResponse;
import com.example.rest.service.contact.service.ContactFilterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestContactController {
    private ContactFilterService contactService;

    @Autowired
    public RestContactController(ContactFilterService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello/contacts")
    public ResponseEntity<Page<ContactDto>> getContacts(@RequestParam(name = "nameFilter") String nameFilter
    , @RequestParam(name = "page", required = false, defaultValue = "1") int page
    , @RequestParam(name = "size", required = false, defaultValue = "100") int size) {

        if (StringUtils.isBlank(nameFilter)) {
            throw new IncorrectContactNameFilterException("Filter name was blank.");
        }

        List<ContactDto> contacts = contactService.getContacts(nameFilter);

        Pageable pagiable = PageRequest.of(page - 1, size);
        int start = (page - 1) * size;
        int end = start + size;

        Page<ContactDto> pages;
        if (end <= contacts.size()) {
            pages = new CustomPageImpl<>(contacts.subList(start, end), pagiable, contacts.size());
            return new ResponseEntity<>(pages, HttpStatus.OK);
        } else {
            if (start < contacts.size()) {
                contacts.size();
                pages = new CustomPageImpl<>(contacts.subList(start, contacts.size()), pagiable, contacts.size());
                return new ResponseEntity<>(pages, HttpStatus.OK);
            }
        }

        throw new ContactsNotFoundException("Contacts with nameFilter = " + nameFilter + " not found.");
    }

    @ExceptionHandler({IncorrectContactNameFilterException.class, ContactsNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleErrors(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
