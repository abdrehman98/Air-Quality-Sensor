package com.example.aqs.sensor;

import com.example.aqs.admin.Admin;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="sensor")
public class Sensor {

@Id
@GeneratedValue
Long recordId;

String name;
String functionality;
String minoptimumvalue;
String maxoptimumvalue;
Boolean status;        // what is this?
Long ctrated_by_admin_id;
Timestamp createdat;
String updated_by_admin_id;
Timestamp updatedat;

/*@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinColumn(name="admin_id", referencedColumnName = "id")
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }*/

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Timestamp getCreatedat() {
        return createdat;
    }

    public Timestamp getUpdatedat() {
        return updatedat;
    }


    public Long getCtrated_by_admin_id() {
        return ctrated_by_admin_id;
    }

    public void setCtrated_by_admin_id(Long ctrated_by_admin_id) {
        this.ctrated_by_admin_id = ctrated_by_admin_id;
    }

    public void setCreatedat(Timestamp createdat) {
        this.createdat = createdat;
    }

    public String getUpdated_by_admin_id() {
        return updated_by_admin_id;
    }

    public void setUpdated_by_admin_id(String updated_by_admin_id) {
        this.updated_by_admin_id = updated_by_admin_id;
    }

    public void setUpdatedat(Timestamp updatedat) {
        this.updatedat = updatedat;
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



}
