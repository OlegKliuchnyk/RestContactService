package com.example.rest.service.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageImpl<T> extends PageImpl<T> {
    public CustomPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomPageImpl(List<T> content) {
        super(content);
    }

    @JsonProperty("contacts")
    @Override
    public List<T> getContent() {
        return super.getContent();
    }
}
