package com.example.aqs_new.device.sensor;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Sensor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @NotNull
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Timestamp addedAt;

    // Added by
    // Admin

    @Column(nullable = false)
    private boolean active;


}
