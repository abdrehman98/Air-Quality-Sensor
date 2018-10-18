package com.example.aqs.datapacket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="datapacket")
public class Datapacket {

@Id
@GeneratedValue
Long id;
Long deviceid;
Long sensorid;
Time receivetime;
String value;
Boolean status;

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

    public Long getSensorid() {
        return sensorid;
    }

    public void setSensorid(Long sensorid) {
        this.sensorid = sensorid;
    }

    public Time getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Time receivetime) {
        this.receivetime = receivetime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
