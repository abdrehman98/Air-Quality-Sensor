package com.example.aqs.codeversion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeversionRepository extends JpaRepository<Codeversion, Long> {
    public Codeversion findCodeversionByCodeversion(String codeversion);
}
