package com.example.aqs.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository< Admin, Long> {
    public Admin findAdminByEmail(String email);
}
