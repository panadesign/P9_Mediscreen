package com.mediscreen.ms_clientui;

import com.mediscreen.ms_clientui.config.PatientMocks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MsClientuiApplicationTests {
@MockBean
private PatientMocks patientMocks;
	@Test
	void contextLoads() {
	}

}
