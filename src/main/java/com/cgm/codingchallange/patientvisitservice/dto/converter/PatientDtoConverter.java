package com.cgm.codingchallange.patientvisitservice.dto.converter;

import com.cgm.codingchallange.patientvisitservice.dto.PatientDto;
import com.cgm.codingchallange.patientvisitservice.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoConverter {
    public Patient from(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setSurname(patientDto.getSurname());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setSocialSecurityNumber(patientDto.getSocialSecurityNumber());
        return patient;
    }

    public PatientDto to(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setSurname(patient.getSurname());
        patientDto.setBirthDate(patient.getBirthDate());
        patientDto.setSocialSecurityNumber(patient.getSocialSecurityNumber());
        return patientDto;
    }
}
