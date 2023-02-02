package com.mediscreen.ms_clientui.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.config.PatientMocks;
import com.mediscreen.ms_clientui.config.WireMockConfig;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
class PatientControllerTest {

    @Autowired
    private WireMockServer mockPatientService;

    @Autowired
    private MicroServicePatientProxy microServicePatientProxy;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientController patientController;

    @Autowired
    private PatientMocks patientMocks;

    private PatientBean patientBean;


    @BeforeEach
    void setUp() throws IOException {
        PatientMocks.setupMockPatientsResponse(mockPatientService);
        patientBean = new PatientBean(3, "lastname", "firstname", "M", LocalDate.of(2000, 6, 23), "address", "phone");
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void whenGetPatient_thenPatientsShouldBeReturned() {
        assertFalse(microServicePatientProxy.getAllPatients().isEmpty());
    }

    @Test
    public void whenGetPatients_thenTheCorrectPatientsShouldBeReturned() {
        LocalDate birthdateJeremy = LocalDate.of(1968, 6, 22);
        LocalDate birthdateRomelie = LocalDate.of(1952, 9, 27);
        assertEquals(2, microServicePatientProxy.getAllPatients()
                .stream()
                .map(PatientBean::getLastname)
                .count());
    }

    @Test
    void home() throws Exception {
        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPatients() throws Exception {
        patientMocks.mappingBuilder("/patients", 200, List.of(new PatientBean()));
        mockMvc.perform(get("/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientById() throws Exception {
        patientMocks.mappingBuilder("/patients/1", 200, new PatientBean());

        mockMvc.perform(get("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void formUpdatePatient() throws Exception {
        patientMocks.mappingBuilder("/patients/1", 200, new PatientBean());

        mockMvc.perform(get("/patients/1/edit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void formAddPatient() throws Exception {
        patientMocks.mappingBuilder("/patients/", 200, new PatientBean());

        mockMvc.perform(get("/patients/add")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addPatient() throws Exception {
        patientMocks.mappingPost("/patients", 201, patientBean);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/patients")
                        .content(asJsonString(patientBean))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updatePatient() throws Exception {
        patientMocks.mappingPost("/patients/2", 200, new PatientBean());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/patients/2/edit")
                        .content(asJsonString(new PatientBean(2, "patientBean.getLastname()", "atientBean.getFirstname()", "patientBean.getGender()", LocalDate.now(), "patientBean.getAddress()", "patientBean.getPhone()")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Primary
    public static ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String asJsonString(final Object obj) {
        try {
            return objectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}