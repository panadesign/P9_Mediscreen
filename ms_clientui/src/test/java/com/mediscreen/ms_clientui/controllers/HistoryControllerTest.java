package com.mediscreen.ms_clientui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.ms_clientui.beans.HistoryBean;
import com.mediscreen.ms_clientui.config.HistoryProxyMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HistoryControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HistoryProxyMocks historyMocks;

    private HistoryBean historyBean;

    @BeforeEach
    void setUp() {
        historyMocks.resetAll();
        this.historyBean = new HistoryBean(1, new ArrayList<>());
    }


    @Test
    void getHistoryById() throws Exception {
        historyMocks.mappingGet("/patients/1/history", 200, historyBean);

        mockMvc.perform(get("/patients/1/history"))
                .andExpect(status().isOk());
    }

    @Test
    void getHistoryAddForm() throws Exception {
        historyMocks.mappingGet("/patients/1/history/add", 200);

        mockMvc.perform(get("/patients/1/history/add"))
                .andExpect(status().isOk());
    }

    @Test
    void addHistoryError() throws Exception {
        historyMocks.mappingPost("/patients/1/history/add?note=Test2");

        mockMvc.perform(post("/patients/1/history/add")
                        .param("note", ""))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void addHistory() throws Exception {
        historyMocks.mappingPost("/patients/1/history/add?note=Test2");

        mockMvc.perform(post("/patients/1/history/add")
                        .param("note", "Test2"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }


    private String asJsonString(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }


}