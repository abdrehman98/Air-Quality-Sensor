package com.example.aqs_new.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
public Sensor findSensorByName(String name);

}
