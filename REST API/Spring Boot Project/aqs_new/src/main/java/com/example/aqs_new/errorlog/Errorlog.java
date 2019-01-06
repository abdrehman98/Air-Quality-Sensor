package com.example.aqs_new.errorlog;

import com.example.aqs_new.devices.Devices;
import com.example.aqs_new.error.Error;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="errorlog")
public class Errorlog {

    @Id
    @GeneratedValue
    Long recordid; //Note: record id is used to identify records uniquely because device id can be redundant...


    Long deviceid;
    Timestamp erroroccurtime;
    Long errorcode;

    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */

    @ManyToOne
    private Error error;


    @ManyToOne
    private Devices device;

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public Timestamp getErroroccurtime() {
        return erroroccurtime;
    }

    public void setErroroccurtime(Timestamp erroroccurtime) {
        this.erroroccurtime = erroroccurtime;
    }

    public Long getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Long errorcode) {
        this.errorcode = errorcode;
    }


}
