package com.mediscreen.ms_clientui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.config.PatientProxyMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientProxyMocks patientMocks;

    private PatientBean patientBean;

    @BeforeEach
    void setUp() {
        patientMocks.resetAll();
        this.patientBean = new PatientBean(3, "lastname", "firstname", "M", LocalDate.of(2000, 6, 23), "address", "phone");
    }

    @Test
    void getAllPatients() throws Exception {
        patientMocks.mappingGet("/patients", 200, List.of(patientBean));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById() throws Exception {
        patientMocks.mappingGet("/patients/1", 200, patientBean);

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    void formUpdatePatient() throws Exception {
        patientMocks.mappingGet("/patients/1", 200, patientBean);

        mockMvc.perform(get("/patients/1/edit"))
                .andExpect(status().isOk());
    }

    @Test
    void formAddPatient() throws Exception {
        patientMocks.mappingGet("/patients/", 200, List.of(patientBean));

        mockMvc.perform(get("/patients/add"))
                .andExpect(status().isOk());
    }

    @Test
    void addPatient() throws Exception {
        patientMocks.mappingPost("/patients");

        mockMvc.perform(post("/patients")
                        .param("lastname", "Test")
                        .param("firstname", "Test2")
                        .param("birth", "2020-02-15")
                        .param("gender", "F")
                        .param("address", "2 Warren Street")
                        .param("phone", "387-866-1399"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patients"))
                .andReturn();
    }

    @Test
    void addPatientFailed() throws Exception {
        patientMocks.mappingPost("/patients");

        mockMvc.perform(post("/patients"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("patient/patientAdd"))
                .andReturn();
    }

    @Test
    void updatePatient() throws Exception {
        patientMocks.mappingGet("/patients", 200, List.of(patientBean));
        patientMocks.mappingPost("/patients/2", 200, patientBean);

        mockMvc.perform(post("/patients/2/edit")
                        .param("lastname", "Test")
                        .param("firstname", "Test2")
                        .param("birth", "2020-02-15")
                        .param("gender", "F")
                        .param("address", "2 Warren Street")
                        .param("phone", "387-866-1399"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updatePatientFailed() throws Exception {
        patientMocks.mappingGet("/patients", 200, List.of(patientBean));
        patientMocks.mappingPost("/patients/2", 200, patientBean);

        mockMvc.perform(post("/patients/2/edit")
                        .param("lastname", "Test")
                        .param("firstname", "")
                        .param("birth", "2020-02-15")
                        .param("gender", "F")
                        .param("address", "2 Warren Street")
                        .param("phone", "387-866-1399"))
                .andExpect(status().is3xxRedirection());
    }

    private String asJsonString(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }


}