package com.example.aqs.devices;

import com.example.aqs.codeversion.Codeversion;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="devices")
public class Devices {

    @Id
    @GeneratedValue
    Long id;
    Long sensor_combination_code;
    String code_version;
    Boolean status;
    String created_by;
    Time created_at;
    String updated_by;
    Time updated_at;

    @ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinColumn(name="device_id", referencedColumnName = "id")
    private Codeversion codeversion;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensor_combination_code() {
        return sensor_combination_code;
    }

    public void setSensor_combination_code(Long sensor_combination_code) {
        this.sensor_combination_code = sensor_combination_code;
    }

    public String getCode_version() {
        return code_version;
    }

    public void setCode_version(String code_version) {
        this.code_version = code_version;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Time getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Time created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Time getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Time updated_at) {
        this.updated_at = updated_at;
    }



}
