package com.example.aqs_new.device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long>
{
    public Device findDeviceById(Long deviceid);
}
