package com.example.aqs.datapacket;

import com.example.aqs.devices.Devices;
import com.example.aqs.values.Values;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="datapacket")
public class Datapacket {

@Id
@GeneratedValue
     Long id;          // id of packet
     Long deviceid;    //device id
     //Long sensorid;
     Time receivetime; // packet receiving time
     Values[] values ;

    public Values[] getValues() {
        return values;
    }

    public void setValues(Values[] values) {
        this.values = values;
    }

    Boolean status;   // is this a stored packet or recent packet?


    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Long getSensorid() {
        return sensorid;
    }

    public void setSensorid(Long sensorid) {
        this.sensorid = sensorid;
    }*/

    public Time getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Time receivetime) {
        this.receivetime = receivetime;
    }
/*
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
*/
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
