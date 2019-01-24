package com.example.aqs_new.publicside;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicsideRepository extends JpaRepository<Publicside,Long > {
    public Publicside findPublicsideByDeviceid(Long deviceid);
}
