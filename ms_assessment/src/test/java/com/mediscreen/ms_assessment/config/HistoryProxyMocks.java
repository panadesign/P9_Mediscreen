package com.mediscreen.ms_assessment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class HistoryProxyMocks {

    @Autowired
    @Qualifier("mockHistoryProxy")
    private WireMockServer mockHistoryProxy;

    @Autowired
    private ObjectMapper objectMapper;


    public void resetAll() {
        mockHistoryProxy.resetAll();
    }

    public void mappingGet(String url, int status) {
        mockHistoryProxy.stubFor(
                get(urlEqualTo(url))
                        .willReturn(getResponse(status)
                        )
        );
    }

    public void mappingGet(String url, int status, Object body) {
        mockHistoryProxy.stubFor(
                get(urlEqualTo(url))
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