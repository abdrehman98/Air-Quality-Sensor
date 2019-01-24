package com.example.aqs_new.partner;


import javax.persistence.*;

@Entity
@Table
public class Partner {

    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String password;
    @Column(unique = true,nullable = false)
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
