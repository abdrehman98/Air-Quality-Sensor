package com.example.aqs.location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
public Location findLocationByDeviceid(Long deviceid);
}
