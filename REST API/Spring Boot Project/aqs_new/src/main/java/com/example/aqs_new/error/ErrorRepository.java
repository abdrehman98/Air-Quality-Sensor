package com.example.aqs_new.error;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Long> {

    public Error findErrorByErrorcode(Long errorcode);
}
