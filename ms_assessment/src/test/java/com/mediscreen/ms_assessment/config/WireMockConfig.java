package com.mediscreen.ms_assessment.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfig {

    @Bean(name = "mockPatientProxy", initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockPatientProxy() {
        return new WireMockServer(8081);
    }


    @Bean(name = "mockHistoryProxy", initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockHistoryProxy() {
        return new WireMockServer(8082);
    }

}
