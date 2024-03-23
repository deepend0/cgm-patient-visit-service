package com.cgm.codingchallange.patientvisitservice.controller;

import com.cgm.codingchallange.patientvisitservice.dto.PatientDto;
import com.cgm.codingchallange.patientvisitservice.dto.converter.PatientDtoConverter;
import com.cgm.codingchallange.patientvisitservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PatientController {

    private final PatientService patientService;
    private final PatientDtoConverter patientDtoConverter;

    @Autowired
    public PatientController(PatientService patientService, PatientDtoConverter patientDtoConverter) {
        this.patientService = patientService;
        this.patientDtoConverter = patientDtoConverter;
    }

    @GetMapping(path = "/patient", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<PatientDto> getPatients() {
        return patientService.getPatients().stream().map(patientDtoConverter::to).collect(Collectors.toList());
    }
}
