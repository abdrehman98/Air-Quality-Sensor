package com.example.aqs.error;

import com.example.aqs.admin.Admin;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="error")
public class Error {

@Id
@GeneratedValue
Long recordid;

Long errorcode; //represents errorcode
String  description;
Boolean status;
Long  created_by_admin_id;
Timestamp createdat;
Long  updated_by_admin_id;
Timestamp updatedat;


    //Relationshipsfetch = FetchType.EAGER,
// /*********One to Many Relation with Audit************/
    @ManyToOne( fetch= FetchType.EAGER,cascade= {CascadeType.ALL})
    @JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;


    public Long getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Long errorcode) {
        this.errorcode = errorcode;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getUpdated_by_admin_id() {
        return updated_by_admin_id;
    }

    public void setUpdated_by_admin_id(Long updated_by_admin_id) {
        this.updated_by_admin_id = updated_by_admin_id;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

    public Timestamp getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Timestamp updatedat) {
        this.updatedat = updatedat;
    }
}
