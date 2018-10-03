package com.example.aqs.sensor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="sensor")
public class Sensor {

@Id
@GeneratedValue
Long id;
String name;
String functionality;
String minoptimumvalue;
String maxoptimumvalue;
Boolean status;
String ctratedby;
Time createdat;
String updatedby;
Time updatedat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getMinoptimumvalue() {
        return minoptimumvalue;
    }

    public void setMinoptimumvalue(String minoptimumvalue) {
        this.minoptimumvalue = minoptimumvalue;
    }

    public String getMaxoptimumvalue() {
        return maxoptimumvalue;
    }

    public void setMaxoptimumvalue(String maxoptimumvalue) {
        this.maxoptimumvalue = maxoptimumvalue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCtratedby() {
        return ctratedby;
    }

    public void setCtratedby(String ctratedby) {
        this.ctratedby = ctratedby;
    }

    public Time getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Time createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Time getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Time updatedat) {
        this.updatedat = updatedat;
    }
}
