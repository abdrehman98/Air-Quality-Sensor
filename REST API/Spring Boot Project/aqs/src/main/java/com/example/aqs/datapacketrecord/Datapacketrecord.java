package com.example.aqs.datapacketrecord;

import com.example.aqs.devices.Devices;
import com.example.aqs.values.Values;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="datapacketrecord")


public class Datapacketrecord {

    @Id
    @GeneratedValue
    Long id;          // id of packet
    Long deviceid;    //device id
    String sensorname;
    Time receivetime; // packet receiving time
    Long value ;

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

    public String getSensorname() {
        return sensorname;
    }

    public void setSensorname(String sensorname) {
        this.sensorname = sensorname;
    }

    public Time getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Time receivetime) {
        this.receivetime = receivetime;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
