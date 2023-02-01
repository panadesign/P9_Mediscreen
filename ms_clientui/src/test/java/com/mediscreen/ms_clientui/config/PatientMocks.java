package com.mediscreen.ms_clientui.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.client.WireMockBuilder;
import lombok.SneakyThrows;
import lombok.With;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import org.springframework.stereotype.Component;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

@Component
public class PatientMocks {

    @Autowired
    private WireMockServer mockService;
    @Autowired
    private ObjectMapper objectMapper;


    public void mappingBuilder(String url, int status) {
        mockService.stubFor(
                WireMock
                        .get(WireMock.urlEqualTo(url))
                        .willReturn(getResponse(status)
                        )
        );
    }

    @SneakyThrows
    public void mappingBuilder(String url, int status, Object body) {
        mockService.stubFor(
                WireMock
                        .get(WireMock.urlEqualTo(url))
                        .willReturn(getResponse(status)
                                .withBody(objectToString(body))
                        )
        );
    }

    private static ResponseDefinitionBuilder getResponse(int status) {
        return WireMock.aResponse()
                .withStatus(status)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    private String objectToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static void setupMockPatientsResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/patients"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        PatientMocks.class.getClassLoader().getResourceAsStream("payload/get-patient-response.json"),
                                        defaultCharset()))));
    }
}