package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.service.ContactCacheService;
import com.example.rest.service.contact.service.ContactCachedFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DefaultCachedFilterService implements ContactCachedFilterService {
    private ContactCacheService contactCacheService;

    @Autowired
    public DefaultCachedFilterService(ContactCacheService contactCacheService) {
        this.contactCacheService = contactCacheService;
    }

    @Override
    public List<ContactDto> getContacts(String nameFilter) {
        Pattern filter = Pattern.compile(nameFilter);
        return contactCacheService.getAllContacts().stream()
                .parallel()
                .filter(item -> !filter.matcher(item.getName()).find())
                .map(item -> new ContactDto(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }
}
