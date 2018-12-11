package com.example.aqs.previouslocation;

import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="previouslocation")

public class Previouslocation {



    @Id

    @GeneratedValue
    Long recordid;

    Long deviceid;
    Timestamp locationtime;
    Long longitude;
    Long Latitude;



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

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return Latitude;
    }

    public void setLatitude(Long latitude) {
        Latitude = latitude;
    }
}
