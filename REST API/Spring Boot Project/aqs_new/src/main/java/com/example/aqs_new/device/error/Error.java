package com.example.aqs_new.device.error;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Error {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;
}
