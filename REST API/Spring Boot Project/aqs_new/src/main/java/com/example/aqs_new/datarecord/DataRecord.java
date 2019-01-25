package com.example.aqs_new.datarecord;

import com.example.aqs_new.device.Device;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="datarecord")
public class DataRecord {
    @Id
    @GeneratedValue
    Long id;

    @GeneratedValue
    Timestamp createdAt;

    @Column(nullable=false)
    Double Temperature;

    @Column(nullable=false)
    Double Humidity;

    @Column(nullable=false)
    Double PM1;

    @Column(nullable=false)
    Double PM25;

    @Column(nullable=false)
    Double PM10;

    @Column(nullable=false)
    Long AQI;

    @ManyToOne
    private Device device;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }

    public Double getPM1() {
        return PM1;
    }

    public void setPM1(Double PM1) {
        this.PM1 = PM1;
    }

    public Double getPM25() {
        return PM25;
    }

    public void setPM25(Double PM25) {
        this.PM25 = PM25;
    }

    public Double getPM10() {
        return PM10;
    }

    public void setPM10(Double PM10) {
        this.PM10 = PM10;
    }

    public Long getAQI() {
        return AQI;
    }

    public void setAQI(Long AQI) {
        this.AQI = AQI;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
