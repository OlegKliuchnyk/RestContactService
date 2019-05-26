package com.example.rest.service.contact.service;

import com.example.rest.service.contact.dto.ContactDto;

import java.util.List;

public interface ContactCachedFilterService {
    List<ContactDto> getContacts(String nameFilter);
}
