package com.example.aqs_new.location;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    double longitude;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    Long  deviceId;
    @Column( columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp locationtime;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getLocationtime() {
        return locationtime;
    }

    public void setLocationtime(Timestamp locationtime) {
        this.locationtime = locationtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}