package com.example.aqs.errorlog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="errorlog")
public class Errorlog {

    @Id
    @GeneratedValue
    Long recordid;

    Long deviceid;
    Timestamp erroroccurtime;
    Long errorcode;

    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */
    /* how to implement its relation */


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
