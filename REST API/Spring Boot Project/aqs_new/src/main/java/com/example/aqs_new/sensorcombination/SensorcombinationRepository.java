package com.example.aqs_new.sensorcombination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorcombinationRepository extends JpaRepository<Sensorcombination, Long> {

    public Sensorcombination findSensorcombinationBySensorcombinationcode(Long code);
}
