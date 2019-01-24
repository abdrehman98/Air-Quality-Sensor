package com.example.aqs_new.partnerpreviouspm25;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Partnerpreviouspm25Repesitory extends JpaRepository<Partnerpreviouspm25,Long> {
    public Iterable<Partnerpreviouspm25> findAllByDeviceid(Long deviceid);
}
