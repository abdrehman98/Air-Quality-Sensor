package com.example.aqs.devices;

import com.example.aqs.codeversion.Codeversion;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="devices")
public class Devices {

    @Id
    @GeneratedValue
    Long recordid;
    Long deviceid;
    Long sensor_combination_code;
    Long code_version;
    Boolean status;
    Long created_by_admin_id;
    Timestamp created_at;
    Long updated_by_admin_id;
    Timestamp updated_at;


/*@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
        @JoinColumn(name="device_id", referencedColumnName = "id")
        private Codeversion codeversion;

    */

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }



    public Timestamp getCreated_at() {
        return created_at;
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

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }



    public void setCode_version(Long code_version) {
        this.code_version = code_version;
    }

    public Long getCode_version() {
        return code_version;
    }




    public Long getSensor_combination_code() {
        return sensor_combination_code;
    }

    public void setSensor_combination_code(Long sensor_combination_code) {
        this.sensor_combination_code = sensor_combination_code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }




}
