package com.example.aqs_new.sensorparameter;

import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.sensor.Sensor;

import javax.persistence.*;

@Entity
@Table(name="sensorparameter")
public class Sensorparameter {
    @Id
    Long id;

    String sensorname;
    String parameter;

    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/
    /*relation with sensor*/

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Sensor sensor;


    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
