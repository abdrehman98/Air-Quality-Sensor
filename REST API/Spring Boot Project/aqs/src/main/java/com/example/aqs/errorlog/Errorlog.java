package com.example.aqs.errorlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="errorlog")
public class Errorlog {

    @Id
    @GeneratedValue
    Long id;
    Long deviceid;
    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }

    Time occurtime;
    Long errorcode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getOccurtime() {
        return occurtime;
    }

    public void setOccurtime(Time occurtime) {
        this.occurtime = occurtime;
    }

    public Long getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Long errorcode) {
        this.errorcode = errorcode;
    }


}
