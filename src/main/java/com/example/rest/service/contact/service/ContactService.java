package com.example.rest.service.contact.service;

import com.example.rest.service.contact.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getContacts(String nameFilter);
}
