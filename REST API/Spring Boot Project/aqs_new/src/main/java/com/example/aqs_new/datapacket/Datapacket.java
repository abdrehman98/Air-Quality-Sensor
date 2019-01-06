package com.example.aqs_new.datapacket;
import com.example.aqs_new.values.Values;

//@Entity
//@Table(name="datapacket")
public class Datapacket {

     //@Id
     //@GeneratedValue
     //Long Packetid;         // id of packet

     Long deviceid;         // device id
     //Timestamp receivetime; // packet receiving time
     Values[] values ;      //
     Boolean status;        // is this a stored packet or recent packet?


    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */
    /* device and datapacket relation */



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
