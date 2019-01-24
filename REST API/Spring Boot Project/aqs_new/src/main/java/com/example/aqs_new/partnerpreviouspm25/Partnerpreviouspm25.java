package com.example.aqs_new.partnerpreviouspm25;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="partnerpreviouspm25")
public class Partnerpreviouspm25 {

    @Id
    @GeneratedValue
    Long id;
    Long deviceid;
    Timestamp timestamp;
    Long PM25;

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

    public Long getPM25() {
        return PM25;
    }

    public void setPM25(Long PM25) {
        this.PM25 = PM25;
    }
}
