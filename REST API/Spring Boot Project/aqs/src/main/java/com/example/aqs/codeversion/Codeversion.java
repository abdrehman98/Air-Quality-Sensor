package com.example.aqs.codeversion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="codeversion")
public class Codeversion {

    @Id
    @GeneratedValue
    Long id;
    String codeversion;
    Long sensorcombinationcode;
    String firmware;
    Boolean status;
    String createdby;
    Time createdat;
    String updatedby;
    Time updatedat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeversion() {
        return codeversion;
    }

    public void setCodeversion(String codeversion) {
        this.codeversion = codeversion;
    }

    public Long getSensorcombinationcode() {
        return sensorcombinationcode;
    }

    public void setSensorcombinationcode(Long sensorcombinationcode) {
        this.sensorcombinationcode = sensorcombinationcode;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
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
