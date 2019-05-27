package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.service.ContactFilterService;
import com.example.rest.service.contact.service.FilteredContactCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultFilteredContactCacheService implements FilteredContactCacheService {
    private final ContactFilterService contactCachedFilterService;

    @Autowired
    public DefaultFilteredContactCacheService(ContactFilterService contactCachedFilterService) {
        this.contactCachedFilterService = contactCachedFilterService;
    }

    @Cacheable(cacheNames = "filtered_contacts", key = "{ #nameFilter }")
    @Override
    public List<ContactDto> getContacts(String nameFilter) {
        return contactCachedFilterService.getContacts(nameFilter);
    }
}
