package com.example.aqs.datapacketrecord;

import com.example.aqs.devices.Devices;
import com.example.aqs.values.Values;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="datapacketrecord")


public class Datapacketrecord {

    @Id
    @GeneratedValue
    Long packetrecordid;          // id of packet
    Long deviceid;    //device id
    String sensorname;
    Timestamp receivetime; // packet receiving time
    Long parametervalue ;

    public Long getPacketrecordid() {
        return packetrecordid;
    }

    public void setPacketrecordid(Long packetrecordid) {
        this.packetrecordid = packetrecordid;
    }

    public Timestamp getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Timestamp receivetime) {
        this.receivetime = receivetime;
    }

    public Long getParametervalue() {
        return parametervalue;
    }

    public void setParametervalue(Long parametervalue) {
        this.parametervalue = parametervalue;
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



}
