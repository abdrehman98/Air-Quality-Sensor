package com.example.aqs_new.device.error;

import com.example.aqs_new.device.Device;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ErrorLog {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Timestamp timestamp;

    @ManyToOne
    private Device device;

    // @ManyToOne
    // private
}
