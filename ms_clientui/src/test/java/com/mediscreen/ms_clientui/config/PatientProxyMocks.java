package com.mediscreen.ms_clientui.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.util.StreamUtils.copyToString;

@Component
public class PatientProxyMocks {

    @Autowired
    @Qualifier("mockPatientProxy")
    private WireMockServer mockPatientProxy;

    @Autowired
    private ObjectMapper objectMapper;


    public void resetAll() {
        mockPatientProxy.resetAll();
    }

    public void mappingGet(String url, int status) {
        mockPatientProxy.stubFor(
                get(urlEqualTo(url))
                        .willReturn(getResponse(status)
                        )
        );
    }

    public void mappingGet(String url, int status, Object body) {
        mockPatientProxy.stubFor(
                get(urlEqualTo(url))
                        .willReturn(getResponse(status)
                                .withBody(objectToString(body))
                        )
        );
    }

    public void mappingPost(String url) {
        mockPatientProxy.stubFor(
                any(urlEqualTo(url)).willReturn(aResponse())
        );
    }

    public void mappingPost(String url, int status)  {
        mockPatientProxy.stubFor(
                any(urlEqualTo(url))
                        .willReturn(getResponse(status))
        );
    }

    public void mappingPost(String url, int status, Object body) {
        mockPatientProxy.stubFor(
                any(urlEqualTo(url))
                        .willReturn(getResponse(status)
                                .withBody(objectToString(body))
                        )
        );
    }


    private static ResponseDefinitionBuilder getResponse(int status) {
        return aResponse()
                .withStatus(status)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    }

    @SneakyThrows
    private String objectToString(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}