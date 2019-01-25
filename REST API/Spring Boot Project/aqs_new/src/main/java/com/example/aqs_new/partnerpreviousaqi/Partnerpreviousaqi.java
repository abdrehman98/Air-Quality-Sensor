package com.example.aqs_new.partnerpreviousaqi;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="partnerpreviousaqi")
public class Partnerpreviousaqi {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    Long deviceid;
    @GeneratedValue
    Timestamp timestamp;
    @Column(nullable = false)
    Long aqi;

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

    public Long getAqi() {
        return aqi;
    }

    public void setAqi(Long aqi) {
        this.aqi = aqi;
    }
}
