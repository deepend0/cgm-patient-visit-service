package com.cgm.codingchallange.patientvisitservice.repository;

import com.cgm.codingchallange.patientvisitservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPatient_Id(Long patientId);
}
