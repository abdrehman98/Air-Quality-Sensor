package com.example.aqs_new.partnerpreviouspm25;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="partnerpreviouspm25")
public class Partnerpreviouspm25 {

    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    Long deviceid;
    @GeneratedValue
    Timestamp timestamp;
    @Column(nullable = false)
    double PM25;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getPM25() {
        return PM25;
    }

    public void setPM25(double PM25) {
        this.PM25 = PM25;
    }
}
