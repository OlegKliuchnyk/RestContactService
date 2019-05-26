package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.service.ContactCachedFilterService;
import com.example.rest.service.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultContactService implements ContactService {
    private final ContactCachedFilterService contactCachedFilterService;

    @Autowired
    public DefaultContactService(ContactCachedFilterService contactCachedFilterService) {
        this.contactCachedFilterService = contactCachedFilterService;
    }

    @Cacheable(cacheNames = "filtered_contacts", key = "#nameFilter")
    @Override
    public List<ContactDto> getContacts(String nameFilter) {
        return contactCachedFilterService.getContacts(nameFilter);
    }
}
