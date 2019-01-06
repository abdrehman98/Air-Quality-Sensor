package com.example.aqs_new.codeversion;

import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.sensorcombination.Sensorcombination;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="codeversion")
public class Codeversion {

    @Id
    Long versionid;
    String description;
    Long sensorcombinationcode;
    String firmware_url;
    Boolean status; // Latest or not...
    Long created_by_admin_id;
    Timestamp createdat;
    Long updated_by_admin_id;
    Timestamp updatedat;

    //Long version_precedence;

    @ManyToOne
    //@JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;

    @ManyToOne
    private Sensorcombination sensorcombination;

    public Sensorcombination getSensorcombination() {
        return sensorcombination;
    }

    public void setSensorcombination(Sensorcombination sensorcombination) {
        this.sensorcombination = sensorcombination;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
