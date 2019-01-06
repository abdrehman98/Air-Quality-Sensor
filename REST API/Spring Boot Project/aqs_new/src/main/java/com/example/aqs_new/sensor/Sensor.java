package com.example.aqs_new.sensor;

import com.example.aqs_new.admin.Admin;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="sensor")
public class Sensor {

@Id
Long id;

String name;
String functionality;
String minoptimumvalue;
String maxoptimumvalue;
//Boolean status;        // what is this?
Long ctrated_by_admin_id;
Timestamp createdat;
//String updated_by_admin_id;
//Timestamp updatedat;

    @ManyToOne
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

    public Timestamp getCreatedat() {
        return createdat;
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


}
