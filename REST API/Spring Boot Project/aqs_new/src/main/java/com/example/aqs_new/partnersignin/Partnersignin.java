package com.example.aqs_new.partnersignin;

import com.example.aqs_new.partner.Partner;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="partnersignin")
public class Partnersignin {

    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @GeneratedValue
    Timestamp start;
    @Nullable
    Timestamp end;
    @ManyToOne
    Partner partner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
