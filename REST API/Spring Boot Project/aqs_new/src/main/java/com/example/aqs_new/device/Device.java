package com.example.aqs_new.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="device")
public class Device {
/*192.168.1.15*/

    @Id
    Long id;

    @Column( columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp createdAt;

    @Column(nullable = false)
    Long codeVersionId;

    public Long getCodeVersionId() {
        return codeVersionId;
    }

    public void setCodeVersionId(Long codeVersionId) {
        this.codeVersionId = codeVersionId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}





