package com.example.aqs.codeversion;

import com.example.aqs.admin.Admin;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="codeversion")
public class Codeversion {

    @Id
    @GeneratedValue
    Long id;
    String version;
    Long sensorcombinationcode;
    String firmware;
    Boolean status;
    String createdby;
    Time createdat;
    String updatedby;
    Time updatedat;


    @ManyToOne( fetch= FetchType.EAGER,cascade= {CascadeType.ALL})
    @JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

/*    public String getCodeversion() {
        return codeversion;
    }

    public void setCodeversion(String codeversion) {
        this.codeversion = codeversion;
    }*/

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
