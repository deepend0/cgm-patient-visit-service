package com.cgm.codingchallange.patientvisitservice.controller;

import com.cgm.codingchallange.patientvisitservice.dto.VisitDto;
import com.cgm.codingchallange.patientvisitservice.dto.converter.VisitDtoConverter;
import com.cgm.codingchallange.patientvisitservice.entity.Patient;
import com.cgm.codingchallange.patientvisitservice.entity.Visit;
import com.cgm.codingchallange.patientvisitservice.exception.NotFoundException;
import com.cgm.codingchallange.patientvisitservice.service.PatientService;
import com.cgm.codingchallange.patientvisitservice.service.VisitService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VisitController {

    private final PatientService patientService;
    private final VisitService visitService;

    private final VisitDtoConverter visitDtoConverter;

    public VisitController(PatientService patientService, VisitService visitService, VisitDtoConverter visitDtoConverter){
        this.patientService = patientService;
        this.visitService = visitService;
        this.visitDtoConverter = visitDtoConverter;
    }

    @GetMapping(path = "/visit", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<VisitDto> getVisitsOfPatient(@RequestParam Long patientId) {
        return visitService.getVisitsOfPatient(patientId).stream().map(visitDtoConverter::to).collect(Collectors.toList());
    }

    @GetMapping(path = "/visit/{visitId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public VisitDto getVisit(@PathVariable Long visitId) {
        Optional<VisitDto> visitDtoOptional = visitService.getVisitById(visitId).stream().map(visitDtoConverter::to).findFirst();
        if(visitDtoOptional.isEmpty()) {
            throw new NotFoundException("Visit Not Found");
        }
        return visitService.getVisitById(visitId).stream().map(visitDtoConverter::to).findFirst().get();
    }

    @PostMapping(path = "/visit", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Long createVisit(@RequestBody VisitDto visitDto) {
        Visit visit = visitDtoConverter.from(visitDto);
        Optional<Patient> patientOptional = patientService.getPatientById(visitDto.getPatientId());
        if(patientOptional.isEmpty()) {
            throw new IllegalArgumentException("Patient Not Found");
        }
        visit.setPatient(patientOptional.get());
        return visitService.saveVisit(visit);
    }

    @PutMapping(path = "/visit", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Long updateVisit(@RequestBody VisitDto visitDto) {
        Optional<Visit> visitOptional = visitService.getVisitById(visitDto.getId());
        if(visitOptional.isEmpty()) {
            throw new IllegalArgumentException("Visit Not Found");
        }
        Visit visit = visitOptional.get();
        visitDtoConverter.from(visitDto, visit);

        Optional<Patient> patientOptional = patientService.getPatientById(visitDto.getPatientId());
        if(patientOptional.isEmpty()) {
            throw new IllegalArgumentException("Patient Not Found");
        }
        visit.setPatient(patientOptional.get());
        return visitService.saveVisit(visit);
    }
}
