package com.mediScreen.ms_patient.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediScreen.ms_patient.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.writeValueAsString(obj);
    }

//    @Test
//    void allPatients() throws Exception {
//        mockMvc.perform(get("/patients")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    void patientById() throws Exception {
//        ZonedDateTime dateTime = ZonedDateTime.from(ZonedDateTime.now());
//        Patient patient = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");
//
//        mockMvc.perform(get("/patients/{id}", patient.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updatePatient() throws Exception {
//        String uri = "/patients/3";
//        ZonedDateTime dateTime = ZonedDateTime.from(ZonedDateTime.now());
//        Patient patient = new Patient(3, "Lastname", "Firstname", dateTime, "M", "Address", "12345");
//
//        patient.setPhone("0000");
//        String inputJson = mapToJson(patient);
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        assertEquals("0000", patient.getPhone());
//    }
//
//    @Test
//    void addPatient() throws Exception {
//        ZonedDateTime dateTime = ZonedDateTime.from(ZonedDateTime.now());
//        String uri = "/patients";
//        Patient patient = new Patient();
//        patient.setId(2);
//        patient.setLastname("lastNameTest");
//        patient.setFirstname("firstNameTest");
//        patient.setBirth(dateTime);
//        patient.setGender("F");
//        patient.setAddress("addressTest");
//        patient.setPhone("phoneTest");
//
//        String inputJson = mapToJson(patient);
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//    }
}