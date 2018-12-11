package com.example.aqs.location;

import com.example.aqs.devices.Devices;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="location")
public class Location {

@Id
@GeneratedValue
Long recordid;

Long deviceid;
Timestamp locationtime;
Long longitude;
Long Latitude;


    /* relation qith device */
    /* relation qith device */
    /* relation qith device */
    /* relation qith device */
    /* relation qith device */
    /* relation qith device */
    /* relation qith device */





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
