package com.example.aqs.error;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
