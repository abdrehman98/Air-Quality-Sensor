package com.example.aqs.sensorcombination;

import com.example.aqs.admin.Admin;
import com.example.aqs.codeversion.Codeversion;
import com.example.aqs.devices.Devices;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="sensorcombination")
public class Sensorcombination {

@Id
@GeneratedValue
Long id;

Long sensorcombinationcode;
String description;
Boolean status;  // what???
Long created_by_admin_id;
Timestamp createdat;
Long updated_by_admin_id;
Timestamp updatedat;



    @ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
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

    public Long getSensorcombinationcode() {
        return sensorcombinationcode;
    }

    public void setSensorcombinationcode(Long sensorcombinationcode) {
        this.sensorcombinationcode = sensorcombinationcode;
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
