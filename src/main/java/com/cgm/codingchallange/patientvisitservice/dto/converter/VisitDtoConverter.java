package com.cgm.codingchallange.patientvisitservice.dto.converter;

import com.cgm.codingchallange.patientvisitservice.dto.VisitDto;
import com.cgm.codingchallange.patientvisitservice.entity.Visit;
import com.cgm.codingchallange.patientvisitservice.entity.VisitReason;
import com.cgm.codingchallange.patientvisitservice.entity.VisitType;
import org.springframework.stereotype.Component;

@Component
public class VisitDtoConverter {
    public Visit from(VisitDto visitDto){
        Visit visit = new Visit();
        from(visitDto, visit);
        return visit;
    }
    public void from(VisitDto visitDto, Visit visit){
        visit.setId(visitDto.getId());
        visit.setDateTime(visitDto.getDateTime());
        visit.setVisitType(VisitType.valueOf(visitDto.getVisitType()));
        visit.setVisitReason(VisitReason.valueOf(visitDto.getVisitReason()));
        visit.setFamilyHistory(visitDto.getFamilyHistory());
    }

    public VisitDto to(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setDateTime(visit.getDateTime());
        visitDto.setVisitType(visit.getVisitType().name());
        visitDto.setVisitReason(visit.getVisitReason().name());
        visitDto.setFamilyHistory(visit.getFamilyHistory());
        return visitDto;
    }
}
