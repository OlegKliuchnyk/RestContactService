package com.example.rest.service.contact.controller;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.model.CustomPageImpl;
import com.example.rest.service.contact.service.ContactFilterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
