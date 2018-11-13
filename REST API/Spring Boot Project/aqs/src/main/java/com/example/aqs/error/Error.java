package com.example.aqs.error;

import com.example.aqs.admin.Admin;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="error")
public class Error {

@Id
@GeneratedValue
Long id;
Long errorcode;
String description;
Boolean status;
String createdby;
Time createdat;
String updatedby;
Time updatedat;

//Relationshipsfetch = FetchType.EAGER,
// /*********One to Many Relation with Audit************/
    @ManyToOne( fetch= FetchType.EAGER,cascade= {CascadeType.ALL})
    @JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;

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

    public Long getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(Long errorcode) {
        this.errorcode = errorcode;
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
