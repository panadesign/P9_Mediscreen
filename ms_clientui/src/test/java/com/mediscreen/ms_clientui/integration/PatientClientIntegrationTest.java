package com.mediscreen.ms_clientui.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.mediscreen.ms_clientui.config.PatientMocks;
import com.mediscreen.ms_clientui.config.WireMockConfig;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
class PatientClientIntegrationTest {

    @Autowired
    private WireMockServer mockPatientService;

    @Autowired
    private MicroServicePatientProxy microServicePatientProxy;

    @BeforeEach
    void setUp() throws IOException {
        PatientMocks.setupMockPatientsResponse(mockPatientService);
    }

    @Test
    public void whenGetPatient_thenPatientsShouldBeReturned() {
        assertFalse(microServicePatientProxy.getAllPatients().isEmpty());
    }

    @Test
    public void whenGetPatients_thenTheCorrectPatientsShouldBeReturned() {
        LocalDate birthdateJeremy = LocalDate.of(1968, 6, 22);
        LocalDate birthdateRomelie = LocalDate.of(1952, 9, 27);
        assertTrue(microServicePatientProxy.getAllPatients()
                .stream()
                .map(p -> p.getLastname())
                .count() == 2);
    }
}