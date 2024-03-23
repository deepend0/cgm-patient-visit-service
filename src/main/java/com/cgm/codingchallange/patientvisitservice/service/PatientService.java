package com.cgm.codingchallange.patientvisitservice.service;

import com.cgm.codingchallange.patientvisitservice.entity.Patient;
import com.cgm.codingchallange.patientvisitservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
