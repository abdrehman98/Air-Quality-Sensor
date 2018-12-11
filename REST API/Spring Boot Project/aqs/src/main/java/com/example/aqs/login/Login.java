package com.example.aqs.login;
import javax.persistence.*;

@Entity
@Table(name="login")

public class Login {

    @Id
    @GeneratedValue
    Long recordid;


    String email;
    String password;

    public Long getRecordid() {
        return recordid;
    }

    public void setRecordid(Long recordid) {
        this.recordid = recordid;
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
}
