package com.mediScreen.ms_patient.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.repository.PatientRepository;
import com.mediScreen.ms_patient.service.PatientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@ActiveProfiles("test")
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Autowired
    private PatientServiceImpl patientService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void allPatients() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    void patientById() throws Exception {
        LocalDate dateTime = LocalDate.now();
        Patient patient = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        mockMvc.perform(get("/patients/{id}", patient.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById() throws Exception {
        Optional<Patient> patient = patientService.findById(1);
        mockMvc.perform(get("/patients/{id}", patient.get().getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientByLastname() throws Exception {
        Optional<Patient> patient = patientService.findByLastname("Ferguson");
        mockMvc.perform(get("/patients/{lastname}", patient.get().getLastname())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePatient() throws Exception {
        String uri = "/patients/1";
        LocalDate dateTime = LocalDate.now();
        Patient patient = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        patient.setPhone("0000");
        String inputJson = mapToJson(patient);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("0000", patient.getPhone());
    }

    @Test
    void addPatient() throws Exception {
        String uri = "/patients";

        Patient patient = new Patient(2, "lastNameTest", "firstNameTest", LocalDate.now(), "F", "Address Test", "Phone Test");

        patientRepository.save(patient);

        String inputJson = mapToJson(patient);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    private <T> T responseToObject(ResultActions resultAction, Class<T> objectClass) throws Exception {
        return objectMapper().readValue(resultAction.andReturn().getResponse().getContentAsString(), objectClass);
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }



}