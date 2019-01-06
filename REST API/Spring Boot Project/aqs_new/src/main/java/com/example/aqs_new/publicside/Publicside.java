package com.example.aqs_new.publicside;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="publicside")
public class Publicside {
    @Id
    Long deviceid;
    double longitude;
    double latitude;
    Long aqi;

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
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

    public Long getAqi() {
        return aqi;
    }

    public void setAqi(Long aqi) {
        this.aqi = aqi;
    }
}
