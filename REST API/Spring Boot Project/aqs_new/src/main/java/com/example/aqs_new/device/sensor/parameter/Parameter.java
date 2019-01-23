package com.example.aqs_new.device.sensor.parameter;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;

@Entity
public class Parameter {
    @Id
    private Long id;

    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Timestamp creationTime;

    @Column(nullable = false, unique = true)
    private String parameterName;


    private String description;

    // Admin
    // Creator ID


}
