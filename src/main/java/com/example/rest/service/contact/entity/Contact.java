package com.example.rest.service.contact.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public Contact(String name) {
        this.name = name;
    }
}
