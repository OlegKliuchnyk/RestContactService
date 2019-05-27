package com.example.rest.service.contact.controller;

import com.example.rest.service.contact.dto.ContactDto;
import com.example.rest.service.contact.model.CustomPageImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import static java.util.Collections.singletonList;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest
public class RestContactControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestContactController restContactController;

    @Test
    public void getContactsSuccessResponse() throws Exception {
        final String nameFilter = "^A.*$";
        final int size = 100;
        final int page = 1;

        ContactDto contactDto = new ContactDto(1, "Test");
        List<ContactDto> contacts = singletonList(contactDto);
        Page<ContactDto> pages = new CustomPageImpl<>(contacts, PageRequest.of(page, size), contacts.size());

        ResponseEntity<Page<ContactDto>> responseEntity = new ResponseEntity<>(pages, HttpStatus.OK);

        given(restContactController.getContacts(nameFilter, 1, 100)).willReturn(responseEntity);

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.contacts", hasSize(1)))
        .andExpect(jsonPath("$.contacts[0].name", is(contactDto.getName())));
    }

    @Test
    public void getContactsBadRequest() throws Exception {
        final String nameFilter = "";

        given(restContactController.getContacts(nameFilter, 1, 100)).willReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContactsNoContent() throws Exception {
        final String nameFilter = "^A.*$";

        given(restContactController.getContacts(nameFilter, 1, 100)).willReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        mvc.perform(get("/hello/contacts").param("nameFilter", nameFilter)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}