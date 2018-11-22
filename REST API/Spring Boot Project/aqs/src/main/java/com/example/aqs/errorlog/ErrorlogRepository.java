package com.example.aqs.errorlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorlogRepository extends JpaRepository<Errorlog, Long> {
public Iterable<Errorlog> findAllByDeviceid(Long deviceid);

}
