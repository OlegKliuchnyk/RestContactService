package com.example.rest.service.contact.cache.impl;

import com.example.rest.service.contact.entity.Contact;
import com.example.rest.service.contact.repository.ContactRepository;
import com.example.rest.service.contact.cache.ContactCache;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultContactCache implements ContactCache {
    private final ContactRepository repository;
    @Value("${contact.total.items.per_page:100}")
    private int totalItemsPerPage;

    @Value("${contact.db.total_items:10000}")
    private int totalItemsInsertToDb;

    @Autowired
    public DefaultContactCache(ContactRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        insertToDb();
    }

    private void insertToDb() {
        for (int i = 0; i < totalItemsInsertToDb; i++) {
            String name = RandomStringUtils.randomAlphanumeric(10);
            repository.save(new Contact(name));
        }
    }

    @Cacheable(cacheNames = "contacts")
    @Override
    public List<Contact> getAllContacts() {

        List<Contact> contacts = new ArrayList<>();
        Iterable<Contact> iterable = repository.findAll();
        iterable.forEach(contacts::add);

        return contacts;
    }


}
