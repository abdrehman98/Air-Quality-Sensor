package com.example.aqs_new.location;
import com.example.aqs_new.device.Device;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    Long id;
    Timestamp locationtime;
    double longitude;
    double Latitude;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Device device;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
