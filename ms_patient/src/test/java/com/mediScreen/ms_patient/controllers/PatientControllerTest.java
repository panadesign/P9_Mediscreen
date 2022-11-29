package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class PatientControllerTest {

//    private MockMvc mockMvc;
//    private final WebApplicationContext webApplicationContext;

//    public PatientControllerTest(MockMvc mockMvc, WebApplicationContext webApplicationContext) {
//        this.mockMvc = mockMvc;
//        this.webApplicationContext = webApplicationContext;
//    }
//
//    @BeforeEach
//    public void init() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .build();
//    }
//
//    @Test
//    void allPatients() throws Exception {
//        mockMvc.perform(get("/patients")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void patientsByLastname() throws Exception {
//        Patient patient = new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345");
//
//        mockMvc.perform(get("/patients/{lastname}", patient.getLastname())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}