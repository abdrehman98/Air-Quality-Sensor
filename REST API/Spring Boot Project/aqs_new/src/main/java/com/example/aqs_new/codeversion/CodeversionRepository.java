package com.example.aqs_new.codeversion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeversionRepository extends JpaRepository<Codeversion, Long> {
    public Codeversion findCodeversionById(Long version);
    //public Codeversion findByOrderVersionGreaterThan(Codeversion device_code_version);
    //public Codeversion findOneByMaxVersion();
    //public Codeversion findTopByVersionDesc();
    //public Codeversion findTopByOrderByVersionidDesc();
    //findTopByOrderByIdDesc
}
