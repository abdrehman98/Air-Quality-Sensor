package com.example.aqs_new.devices;

import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.sensorcombination.Sensorcombination;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="devices")
public class Devices {

    @Id
    Long deviceid;
    Long sensor_combination_code;
    Long code_version;
    Boolean status;   // Device is enabled or disabled
    Long created_by_admin_id;
    Timestamp created_at;
    //Long updated_by_admin_id; //Not requird because its automated
    //Timestamp updated_at;     //Not requird because its automated

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Codeversion codeversion;

    @ManyToOne
    private Sensorcombination sensorcombination;


    public Codeversion getCodeversion() {
        return codeversion;
    }

    public void setCodeversion(Codeversion codeversion) {
        this.codeversion = codeversion;
    }

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

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
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
