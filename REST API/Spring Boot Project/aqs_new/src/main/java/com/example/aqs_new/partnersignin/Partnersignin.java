package com.example.aqs_new.partnersignin;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="partnersignin")
public class Partnersignin {

    @Id
    @GeneratedValue
    Long id;
    @Column( columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp start;
    @Nullable
    Timestamp end;
    @Column(nullable = false)
    Long partnerId;

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

}
