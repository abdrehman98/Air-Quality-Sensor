package com.example.aqs_new.error;

import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.errorlog.Errorlog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="error")
public class Error {


//@GeneratedValue
//Long recordid;
@Id
Long errorcode; //represents errorcode
String  description;
Boolean status; //Does error exist or resolved permanently
Long  created_by_admin_id;
Timestamp createdat;
Long  updated_by_admin_id;
Timestamp updatedat;
@OneToMany
//@JoinColumn(name="errorcode_id")
private List<Errorlog> logs=new ArrayList<>();

    @ManyToOne
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
