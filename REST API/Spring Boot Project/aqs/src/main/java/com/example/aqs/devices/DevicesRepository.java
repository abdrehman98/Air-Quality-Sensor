package com.example.aqs.devices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepository extends JpaRepository<Devices, Long> {
    public Devices findDeviceByDeviceid(Long deviceid);
}
