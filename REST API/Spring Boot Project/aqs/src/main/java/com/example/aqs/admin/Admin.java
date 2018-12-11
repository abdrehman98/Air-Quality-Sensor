package com.example.aqs.admin;

import javax.persistence.*;

@Entity
@Table(name="admin")


public class Admin {
    @Id
    @GeneratedValue
    Long id;

    Long adminId;
    String email;
    String password;
    Boolean status;


    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
