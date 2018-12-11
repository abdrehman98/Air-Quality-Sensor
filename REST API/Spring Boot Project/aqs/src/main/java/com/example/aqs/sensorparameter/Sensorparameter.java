package com.example.aqs.sensorparameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sensorparameter")
public class Sensorparameter {
    @Id
    @GeneratedValue
    Long recordid;

    String sensorname;
    String parameter;

    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/


    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public String getSensorname() {
        return sensorname;
    }

    public void setSensorname(String sensorname) {
        this.sensorname = sensorname;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
