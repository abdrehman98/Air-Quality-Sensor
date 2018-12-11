package com.example.aqs.login;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    //public Login findLoginById(Long id);
    public Login findLoginByEmail(String email);
}
