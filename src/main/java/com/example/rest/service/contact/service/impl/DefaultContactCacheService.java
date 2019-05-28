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

    @Value("${contact.db.total_items:10000}")
    private int totalItemsInsertToDb;

    @Autowired
    public DefaultContactCacheService(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }

    @PostConstruct
    private void init() {
        insertToDb();
        getAllContacts();
    }

    private void insertToDb() {
        for (int i = 0; i < totalItemsInsertToDb; i++) {
            String name = RandomStringUtils.randomAlphanumeric(10);
            contactRepo.save(new Contact(name));
        }
    }

    @Cacheable(cacheNames = "contacts")
    @Override
    public List<Contact> getAllContacts() {

        List<Contact> contacts = new ArrayList<>();
        Iterable<Contact> iterable = contactRepo.findAll();
        iterable.forEach(contacts::add);
        System.out.println("DONE");
        return contacts;
    }


}
