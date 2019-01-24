package com.example.aqs_new.error;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Error {

    @Id
    @GeneratedValue
    Long id; //represents errorcode

    @Column(nullable = false)
    String description;

    @GeneratedValue
    Timestamp creationTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

}
