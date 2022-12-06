package com.mediScreen.ms_patient.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void allPatients() throws Exception {
        mockMvc.perform(get("/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

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