package com.example.aqs_new.datapacketrecord;

import com.example.aqs_new.devices.Devices;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="packetrecord")


public class Packetrecord {

    @Id
    @GeneratedValue
    Long packetrecordid;          // id of packet
    Long deviceid;    //device id
    String sensorname;
    String parametername;
    Timestamp receivetime; // packet receiving time
    Long parametervalue ;
    Boolean status;   // is its a current record or a previosly stored data

    @ManyToOne
    private Devices device;

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public Long getPacketrecordid() {
        return packetrecordid;
    }

    public void setPacketrecordid(Long packetrecordid) {
        this.packetrecordid = packetrecordid;
    }

    public String getParametername() {
        return parametername;
    }

    public void setParametername(String parametername) {
        this.parametername = parametername;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
