package com.mediscreen.ms_assessment.controllers;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_assessment.service.AssessmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@ActiveProfiles("test")
class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MicroServicePatientProxy microServicePatientProxy;

    @Autowired
    private MicroServiceHistoryProxy microServiceHistoryProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AssessmentServiceImpl assessmentService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    void getAssessmentById() throws Exception {
        Optional<PatientBean> patient = microServicePatientProxy.getPatientById(1);
        HistoryBean historyBean = microServiceHistoryProxy.get(1);
        mockMvc.perform(get("/patients/{id}/assessment", patient.get().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getAssessmentByLastname() throws Exception {
        Optional<PatientBean> patient = microServicePatientProxy.getPatientByLastname("Rees");
        HistoryBean historyBean = microServiceHistoryProxy.get(1);
        mockMvc.perform(get("/patients/{lastname}/assessment", patient.get().getLastname())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}