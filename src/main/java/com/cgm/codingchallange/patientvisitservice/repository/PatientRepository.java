package com.cgm.codingchallange.patientvisitservice.repository;

import com.cgm.codingchallange.patientvisitservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
