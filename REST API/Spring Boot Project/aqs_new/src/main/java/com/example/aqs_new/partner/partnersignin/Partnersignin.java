package com.example.aqs_new.partner.partnersignin;

import com.example.aqs_new.partner.Partner;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Partnersignin {

    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @GeneratedValue
    Timestamp timestamp;
    @Id
    @ManyToOne
    Partner partner;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
