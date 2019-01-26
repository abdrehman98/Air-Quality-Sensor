package com.example.aqs_new.datarecord;

import javax.persistence.*;

@Entity
@Table(name="datarecord")
public class DataRecord {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    Long deviceId;

    @Column(nullable=false)
    Double temperature;

    @Column(nullable=false)
    Double humidity;

    @Column(nullable=false)
    Double pm1;

    @Column(nullable=false)
    Double pm25;

    @Column(nullable=false)
    Double pm10;

    @Column(nullable=false)
    Long aqi;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Column(name="createdAt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPm1() {
        return pm1;
    }

    public void setPm1(Double pm1) {
        this.pm1 = pm1;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Long getAqi() {
        return aqi;
    }

    public void setAqi(Long aqi) {
        this.aqi = aqi;
    }

}
