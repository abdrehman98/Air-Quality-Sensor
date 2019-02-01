package com.example.aqs_new.partnerpreviousaqi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerpreviousaqiRepository extends JpaRepository<Partnerpreviousaqi,Long> {

    public Iterable<Partnerpreviousaqi> findAllByDeviceid(Long deviceid);
}
