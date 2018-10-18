package com.example.aqs.error;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Long> {

    public Error findErrorByErrorcode(Long errorcode);
}
