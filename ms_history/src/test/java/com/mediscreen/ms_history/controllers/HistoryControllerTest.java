package com.mediscreen.ms_history.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.repository.HistoryRepository;
import com.mediscreen.ms_history.service.HistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryRepository historyRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @MockBean
    private HistoryServiceImpl historyService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void getHistoryById() throws Exception {
        History history = new History(1, new ArrayList<>());

        mockMvc.perform(get("/patients/{id}/history", history.getPatientId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void addNewNote() throws Exception {
        History history = new History(1, new ArrayList<>());
        historyService.addObservation(history.getPatientId(), "new note");
        historyRepository.save(history);

        mockMvc.perform(post("/patients/{id}/history", history.getPatientId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}