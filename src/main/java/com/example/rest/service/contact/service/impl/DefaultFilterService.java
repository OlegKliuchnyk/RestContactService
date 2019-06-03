package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.cache.ContactCache;
import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.entity.Contact;
import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.service.ContactFilterService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DefaultFilterService implements ContactFilterService {
    private final ContactCache contactCache;


    @Autowired
    public DefaultFilterService(ContactCache contactCache) {
        this.contactCache = contactCache;
    }

    @PostConstruct
    private void init() {
        contactCache.getAllContacts();
    }

    @Override
    public List<ContactDto> getContacts(String nameFilter) {
        Pattern filter = Pattern.compile(nameFilter);
        return contactCache.getAllContacts().stream()
                .parallel()
                .filter(item -> !filter.matcher(item.getName()).find())
                .map(item -> new ContactDto(item.getId(), item.getName()))
                .collect(Collectors.toList());
    }
}
