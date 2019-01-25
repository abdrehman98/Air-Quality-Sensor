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
    @GeneratedValue
    Timestamp locationtime;
    @Column(nullable = false)
    double longitude;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    String locationName;

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
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}