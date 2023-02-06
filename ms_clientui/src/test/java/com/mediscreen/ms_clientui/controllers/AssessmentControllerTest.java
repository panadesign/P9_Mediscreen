package com.mediscreen.ms_clientui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.ms_clientui.beans.AssessmentBean;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.config.AssessmentProxyMocks;
import com.mediscreen.ms_clientui.config.PatientProxyMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
    private AssessmentProxyMocks assessmentMocks;

    @Autowired
    private PatientProxyMocks patientMocks;

    private AssessmentBean assessmentBean;
    private PatientBean patientBean;

    @BeforeEach
    void setUp() {
        assessmentMocks.resetAll();
        patientMocks.resetAll();
        this.patientBean = new PatientBean(1, "lastname", "firstname", "M", LocalDate.now(), "address", "phone");
        this.assessmentBean = new AssessmentBean("NONE", "lastname", "firstname", "M", 43);
    }

    @Test
    void getAssessmentById() throws Exception {
        patientMocks.mappingGet("/patients/1", 200, patientBean);
        assessmentMocks.mappingGet("/patients/1/assessment", 200, assessmentBean);

        mockMvc.perform(get("/patients/1/assessment"))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }


}