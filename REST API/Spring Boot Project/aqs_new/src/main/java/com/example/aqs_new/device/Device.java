package com.example.aqs_new.device;

import com.example.aqs_new.codeversion.Codeversion;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="device")
public class Device {


    @Id
    Long id;
    @GeneratedValue
    Timestamp createdAt;
    @ManyToOne
    private Codeversion codeversion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Codeversion getCodeversion() {
        return codeversion;
    }

    public void setCodeversion(Codeversion codeversion) {
        this.codeversion = codeversion;
    }

    
}





