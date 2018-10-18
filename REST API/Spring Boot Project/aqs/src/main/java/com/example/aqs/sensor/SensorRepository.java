package com.example.aqs.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
public Sensor findSensorByName(String name);
}
