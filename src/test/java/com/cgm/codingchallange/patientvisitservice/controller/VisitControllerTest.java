package com.cgm.codingchallange.patientvisitservice.controller;

import com.cgm.codingchallange.patientvisitservice.dto.VisitDto;
import com.cgm.codingchallange.patientvisitservice.dto.converter.VisitDtoConverter;
import com.cgm.codingchallange.patientvisitservice.entity.Patient;
import com.cgm.codingchallange.patientvisitservice.entity.Visit;
import com.cgm.codingchallange.patientvisitservice.service.PatientService;
import com.cgm.codingchallange.patientvisitservice.service.VisitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@WebMvcTest(VisitController.class)
@AutoConfigureDataJpa
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitService visitService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private VisitDtoConverter visitDtoConverter;

    @Test
    public void testGetVisitsOfPatient() throws Exception {
        Long patientId = 1L;
        List<Visit> visits = Arrays.asList(new Visit(), new Visit());

        given(visitService.getVisitsOfPatient(patientId)).willReturn(visits);
        given(visitDtoConverter.to(any(Visit.class))).willReturn(new VisitDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/visit").param("patientId", patientId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

        verify(visitService).getVisitsOfPatient(patientId);
    }

    @Test
    public void testGetVisit() throws Exception {
        Long visitId = 1L;
        Visit visit = new Visit();

        given(visitService.getVisitById(visitId)).willReturn(Optional.of(visit));
        given(visitDtoConverter.to(any(Visit.class))).willReturn(new VisitDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/visit/{visitId}", visitId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateVisit() throws Exception {
        VisitDto visitDto = new VisitDto();
        visitDto.setPatientId(1L);

        given(patientService.getPatientById(visitDto.getPatientId())).willReturn(Optional.of(new Patient()));
        given(visitService.saveVisit(any(Visit.class))).willReturn(1L);
        given(visitDtoConverter.from(any(VisitDto.class))).willReturn(new Visit());

        mockMvc.perform(MockMvcRequestBuilders.post("/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(visitDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(patientService).getPatientById(visitDto.getPatientId());
        verify(visitService).saveVisit(any(Visit.class));
    }

    @Test
    public void testUpdateVisit() throws Exception {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(1L);
        visitDto.setPatientId(1L);

        given(visitService.getVisitById(visitDto.getId())).willReturn(Optional.of(new Visit()));
        given(patientService.getPatientById(visitDto.getPatientId())).willReturn(Optional.of(new Patient()));
        given(visitService.saveVisit(any(Visit.class))).willReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(visitDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(visitService).getVisitById(visitDto.getId());
        verify(patientService).getPatientById(visitDto.getPatientId());
        verify(visitService).saveVisit(any(Visit.class));
    }

    @Test
    public void testHandleBadRequest() throws Exception {

        given(visitService.getVisitById(anyLong())).willThrow(new IllegalArgumentException("Bad request"));

        mockMvc.perform(MockMvcRequestBuilders.get("/visit/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testHandleNotFound() throws Exception {
        given(visitService.getVisitById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/visit/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
