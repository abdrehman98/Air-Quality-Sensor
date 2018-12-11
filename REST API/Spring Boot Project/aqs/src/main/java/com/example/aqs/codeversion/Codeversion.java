package com.example.aqs.codeversion;

import com.example.aqs.admin.Admin;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="codeversion")
public class Codeversion {

    @Id
    @GeneratedValue
    Long recordid;


    Long versionid;
    String description;
    Long sensorcombinationcode;
    String firmware_url;
    Boolean status;
    Long created_by_admin_id;
    Timestamp createdat;
    Long updated_by_admin_id;
    Timestamp updatedat;

    //Long version_precedence;
/*
    @ManyToOne( fetch= FetchType.EAGER,cascade= {CascadeType.ALL})
    @JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;
*/
    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public Long getVersionid() {
        return versionid;
    }

    public void setVersionid(Long versionid) {
        this.versionid = versionid;
    }

    public String getFirmware_url() {
        return firmware_url;
    }

    public void setFirmware_url(String firmware_url) {
        this.firmware_url = firmware_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getSensorcombinationcode() {
        return sensorcombinationcode;
    }

    public void setSensorcombinationcode(Long sensorcombinationcode) {
        this.sensorcombinationcode = sensorcombinationcode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Long getCreated_by_admin_id() {
        return created_by_admin_id;
    }

    public void setCreated_by_admin_id(Long created_by_admin_id) {
        this.created_by_admin_id = created_by_admin_id;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

    public Long getUpdated_by_admin_id() {
        return updated_by_admin_id;
    }

    public void setUpdated_by_admin_id(Long updated_by_admin_id) {
        this.updated_by_admin_id = updated_by_admin_id;
    }

    public Timestamp getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Timestamp updatedat) {
        this.updatedat = updatedat;
    }
}
