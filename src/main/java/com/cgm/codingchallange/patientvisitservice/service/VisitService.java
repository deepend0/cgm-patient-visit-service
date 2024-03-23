package com.cgm.codingchallange.patientvisitservice.service;

import com.cgm.codingchallange.patientvisitservice.entity.Visit;
import com.cgm.codingchallange.patientvisitservice.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getVisitsOfPatient(Long patientId) {
        return visitRepository.findByPatient_Id(patientId);
    }

    public Optional<Visit> getVisitById(Long visitId) {
        return visitRepository.findById(visitId);
    }



    public Long saveVisit(Visit visit) {
        return visitRepository.save(visit).getId();
    }
}
