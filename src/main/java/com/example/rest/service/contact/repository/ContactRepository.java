package com.example.rest.service.contact.repository;

import com.example.rest.service.contact.entity.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
}
