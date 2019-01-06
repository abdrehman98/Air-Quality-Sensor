package com.example.aqs_new.previouslocation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="previouslocation")

public class Previouslocation {



    @Id
    @GeneratedValue
    Long recordid;

    Long deviceid;
    Timestamp locationtime;
    double longitude;
    double Latitude;



    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }


    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public Timestamp getLocationtime() {
        return locationtime;
    }

    public void setLocationtime(Timestamp locationtime) {
        this.locationtime = locationtime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
