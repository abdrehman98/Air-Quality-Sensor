package com.example.aqs_new.errorlog;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="errorlog")
public class Errorlog {

    @Id
    @GeneratedValue
    Long id; //Note: record id is used to identify records uniquely because device id can be redundant...

    @Column(nullable = false)
    Long deviceId;

    @Column(nullable = false)
    Long errorId;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")

    Timestamp occuredAt;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    public Timestamp getOccuredAt() {
        return occuredAt;
    }

    public void setOccuredAt(Timestamp occuredAt) {
        this.occuredAt = occuredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
