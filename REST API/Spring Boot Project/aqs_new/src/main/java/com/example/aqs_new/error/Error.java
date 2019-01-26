package com.example.aqs_new.error;

import javax.persistence.*;

@Entity
@Table(name="error")
public class Error {

    @Id
    @GeneratedValue
    Long id; //represents errorcode

    @Column(nullable = false)
    String description;

    @Column(name="creationTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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

}
