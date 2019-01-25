package com.example.aqs_new.errorlog;

import com.example.aqs_new.device.Device;
import com.example.aqs_new.error.Error;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="errorlog")
public class Errorlog {

    @Id
    @GeneratedValue
    Long id; //Note: record id is used to identify records uniquely because device id can be redundant...

    @ManyToOne
    Device device;

    @GeneratedValue
    Timestamp occuredAt;

    @ManyToOne
    Error error;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Timestamp getOccuredAt() {
        return occuredAt;
    }

    public void setOccuredAt(Timestamp occuredAt) {
        this.occuredAt = occuredAt;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
