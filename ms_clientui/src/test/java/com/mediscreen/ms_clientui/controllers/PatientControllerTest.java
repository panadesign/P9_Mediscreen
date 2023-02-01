package com.mediscreen.ms_clientui.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.config.PatientMocks;
import com.mediscreen.ms_clientui.config.WireMockConfig;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientController patientController;

    @Autowired
    private PatientMocks patientMocks;

    @Autowired
    private MicroServicePatientProxy patientProxy;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private WireMockConfig wireMockConfig;

    @Autowired
    private WireMockServer mockPatientService;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @BeforeEach
    void init() throws IOException {
        PatientMocks.setupMockPatientsResponse(mockPatientService);
    }

    @Test
    void home() throws Exception {
        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
//
//    @Test
//    void getAllPatients() throws Exception {
//        patientMocks.mappingBuilder("/patients", 200, List.of(new PatientBean()));
//        mockMvc.perform(get("/patients")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getPatientById() throws Exception {
//        PatientBean patientBean = new PatientBean(1, "lastnameTest", "firstnameTest", "M", LocalDate.now(), "AddressTest", "phoneTest");;
//
//        when(patientProxy.getPatientById(patientBean.getId())).thenReturn(patientBean);
//
//        mockMvc.perform(get("/patients/{id}", patientBean.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void formUpdatePatient() throws Exception {
//        PatientBean patientBean = new PatientBean(1, "lastnameTest", "firstnameTest", "M", LocalDate.now(), "AddressTest", "phoneTest");;
//        when(patientProxy.getPatientById(patientBean.getId())).thenReturn(patientBean);
//        mockMvc.perform(get("/patients/{id}/edit", patientBean.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

}