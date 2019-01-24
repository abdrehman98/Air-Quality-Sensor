package com.example.aqs_new.partner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner,Long> {
    public Partner findByDeviceid(Long deviceid);
    public Partner findByEmail(String email);
}
