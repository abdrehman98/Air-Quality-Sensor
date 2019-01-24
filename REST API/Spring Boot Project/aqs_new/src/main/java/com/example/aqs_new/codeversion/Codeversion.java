package com.example.aqs_new.codeversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Codeversion
{
    @Id
    Long id;
    @Column(nullable = false)
    String firmware_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirmware_url() {
        return firmware_url;
    }

    public void setFirmware_url(String firmware_url) {
        this.firmware_url = firmware_url;
    }
}
