package com.cgm.codingchallange.patientvisitservice.controller;

import com.cgm.codingchallange.patientvisitservice.dto.PatientDto;
import com.cgm.codingchallange.patientvisitservice.dto.converter.PatientDtoConverter;
import com.cgm.codingchallange.patientvisitservice.dto.converter.VisitDtoConverter;
import com.cgm.codingchallange.patientvisitservice.entity.Patient;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@AutoConfigureDataJpa
public class PatientControllerTest {

    @MockBean
    private PatientService patientService;

    @MockBean
    private VisitService visitService;

    @MockBean
    private PatientDtoConverter patientDtoConverter;

    @MockBean
    private VisitDtoConverter visitDtoConverter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPatients() throws Exception {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());

        given(patientService.getPatients()).willReturn(patients);
        given(patientDtoConverter.to(any(Patient.class))).willReturn(new PatientDto());

        mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(patientService).getPatients();
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
