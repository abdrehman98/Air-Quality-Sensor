package com.example.aqs_new.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository< Admin, Long> {
    public Admin findAdminById(Long adminid);
    public Admin findAdminByEmail(String email);
}
