package com.mediscreen.ms_history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen.ms_history")
public class MsHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsHistoryApplication.class, args);
    }

}
