package com.example.aqs_new.location;

import com.example.aqs_new.devices.Devices;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "location")
public class Location {

    @Id
    Long deviceid;
    Timestamp locationtime;
    double longitude;
    double Latitude;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    //@JoinColumn(name="device_id",referencedColumnName = "deviceid")
    private Devices device=new Devices();


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

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    //@JoinColumn(name="device_id",referencedColumnName = "deviceid")

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }


    public Timestamp getLocationtime() {
        return locationtime;
    }

    public void setLocationtime(Timestamp locationtime) {
        this.locationtime = locationtime;
    }


}
