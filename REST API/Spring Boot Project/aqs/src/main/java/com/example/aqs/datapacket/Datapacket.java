package com.example.aqs.datapacket;

import com.example.aqs.devices.Devices;
import com.example.aqs.values.Values;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="datapacket")
public class Datapacket {

     @Id
     @GeneratedValue
     Long Packetid;         // id of packet

     Long deviceid;         // device id
     Timestamp receivetime; // packet receiving time
     Values[] values ;      //
     Boolean status;        // is this a stored packet or recent packet?


    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */

    public Long getPacketid() {
        return Packetid;
    }

    public void setPacketid(Long packetid) {
        Packetid = packetid;
    }

    public Timestamp getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Timestamp receivetime) {
        this.receivetime = receivetime;
    }

    public Values[] getValues() {
        return values;
    }

    public void setValues(Values[] values) {
        this.values = values;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
