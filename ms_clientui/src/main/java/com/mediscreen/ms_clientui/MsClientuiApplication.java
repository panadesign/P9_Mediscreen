package com.mediscreen.ms_clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.ms_clientui")
public class MsClientuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientuiApplication.class, args);
	}

}
