package com.mediscreen.ms_clientui.config;

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
public class AssessmentProxyMocks {

    @Autowired
    @Qualifier("mockAssessmentProxy")
    private WireMockServer mockAssessmentProxy;

    @Autowired
    private ObjectMapper objectMapper;


    public void resetAll() {
        mockAssessmentProxy.resetAll();
    }

    public void mappingGet(String url, int status, Object body) {
        mockAssessmentProxy.stubFor(
                get(urlEqualTo(url))
                        .willReturn(getResponse(status)
                                .withBody(objectToString(body))
                        )
        );
    }

//    public void mappingPost(String url) {
//        mockAssessmentProxy.stubFor(
//                any(urlEqualTo(url)).willReturn(aResponse())
//        );
//    }
//
//    public void mappingPost(String url, int status, Object body) {
//        mockAssessmentProxy.stubFor(
//                any(urlEqualTo(url))
//                        .willReturn(getResponse(status)
//                                .withBody(objectToString(body))
//                        )
//        );
//    }


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