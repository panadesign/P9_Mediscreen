package com.mediscreen.ms_clientui;

import com.mediscreen.ms_clientui.config.AssessmentProxyMocks;
import com.mediscreen.ms_clientui.config.HistoryProxyMocks;
import com.mediscreen.ms_clientui.config.PatientProxyMocks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MsClientuiApplicationTests {

    @MockBean
    private PatientProxyMocks patientMocks;

    @MockBean
    private AssessmentProxyMocks assessmentMocks;

    @MockBean
    private HistoryProxyMocks historyMocks;

	@Test
	void contextLoads() {
	}

}
