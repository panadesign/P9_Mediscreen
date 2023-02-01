package com.mediscreen.ms_clientui.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.mediscreen.ms_clientui.config.PatientMocks;
import com.mediscreen.ms_clientui.config.WireMockConfig;
import com.mediscreen.ms_clientui.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

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
    public void whenGetBooks_thenBooksShouldBeReturned() {
        assertFalse(microServicePatientProxy.getAllPatients().isEmpty());
    }
}