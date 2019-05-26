package com.example.rest.service.contact.service.impl;

import com.example.rest.service.contact.entity.Contact;
import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.service.ContactCacheService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultContactCacheService implements ContactCacheService {
    private final ContactRepository contactRepo;
    @Value("${contact.total.items.per_page:100}")
    private int totalItemsPerPage;

    @Autowired
    public DefaultContactCacheService(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }

    @PostConstruct
    private void init() {
        int totalDbItems = 1_000;
        for (int i = 0; i < totalDbItems; i++) {
            String name = RandomStringUtils.randomAlphanumeric(10);
            contactRepo.save(new Contact(name));
        }
    }

    @Cacheable(cacheNames = "contacts")
    @Override
    public List<Contact> getAllContacts() {

        double countItems = contactRepo.count();
        int pages = (int) Math.ceil(countItems / totalItemsPerPage);
        List<Contact> contacts = new ArrayList<>();
        Iterable<Contact> iterable = contactRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        iterable.forEach(contacts::add);

        return contacts;
    }
}