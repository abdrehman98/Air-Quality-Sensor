package com.example.aqs.previouslocation;

import java.sql.Time;
import javax.persistence.*;

@Entity
@Table(name="previouslocation")

public class Previouslocation {



    @Id

    @GeneratedValue
    Long id;
    Long deviceid;
    Time locationtime;
    String longitude;
    String Latitude;



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

    public Time getLocationtime() {
        return locationtime;
    }

    public void setLocationtime(Time locationtime) {
        this.locationtime = locationtime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

}
