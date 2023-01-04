package com.mediscreen.ms_clientui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.ZonedDateTime;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.ms_clientui")
public class MsClientuiApplication {
	public static void main(String[] args) {

		SpringApplication.run(MsClientuiApplication.class, args);
	}

}
