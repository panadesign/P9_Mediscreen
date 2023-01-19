package com.mediscreen.ms_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.ms_assessment")
public class MsAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAssessmentApplication.class, args);
	}

}
