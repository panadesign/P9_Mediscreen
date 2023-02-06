package com.mediscreen.ms_assessment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.ms_assessment.beans.GenderEnum;
import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.config.HistoryProxyMocks;
import com.mediscreen.ms_assessment.config.PatientProxyMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssessmentControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientProxyMocks patientMocks;

    @Autowired
    private HistoryProxyMocks historyMocks;

    private PatientBean patientBean;
    private HistoryBean historyBean;

    @BeforeEach
    void setUp() {
        patientMocks.resetAll();
        historyMocks.resetAll();
        this.patientBean = new PatientBean(3, "dylan", "bob", GenderEnum.M, LocalDate.of(2000, 6, 23), "address", "phone");
        this.historyBean = new HistoryBean(3, new ArrayList<>());
    }

    @Test
    void getAssessmentById() throws Exception {
        patientMocks.mappingGet("/patients/3", 200, patientBean);
        historyMocks.mappingGet("/patients/3/history", 200, historyBean);

        mockMvc.perform(get("/patients/3/assessment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}