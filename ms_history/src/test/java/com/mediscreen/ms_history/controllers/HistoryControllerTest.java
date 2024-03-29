package com.mediscreen.ms_history.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = HistoryControllerTest.MongoDbInitializer.class)
@Slf4j
class HistoryControllerTest {

   private static MongoDbContainer mongoDbContainer;

   @Autowired
   private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HistoryService historyService;

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDbContainer = new MongoDbContainer();
        mongoDbContainer.start();
    }

    @Test
    void getHistoryById() throws Exception {
        //GIVEN
        History history = new History(1, new ArrayList<>());

        // WHEN
        ResultActions resultAction = mockMvc.perform(get("/patients/{id}/history", history.getPatientId())
                        .contentType(MediaType.APPLICATION_JSON));

        //THEN
        resultAction.andExpect(status().isOk());

        History response = responseToObject(resultAction, History.class);

        assertThat(response)
                .isNotNull();
    }

    @Test
    void addNewNote() throws Exception {
        //GIVEN
        History history = new History(1, new ArrayList<>());
        historyService.addObservation(history.getPatientId(), "new note");

        // WHEN
        ResultActions resultAction = mockMvc.perform(post("/patients/{id}/history/add", history.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .param("note", "blablabla"));

        //THEN
        resultAction.andExpect(status().isCreated());

        History response = responseToObject(resultAction, History.class);

        assertThat(response)
                .isNotNull()
                .satisfies(r -> {
                    assertThat(r.getPatientId()).isEqualTo(1);
                    assertThat(r.getObservations())
                            .hasSize(5);
                });
    }

    @Test
    void updateHistory() throws Exception {
        List<History.Observation> observations = new ArrayList<>();
        History.Observation observation = new History.Observation(1L, "text", LocalDate.now());
        observations.add(observation);

        History history = new History(1, new ArrayList<>());
        historyService.updateObservation(history.getPatientId(), observation.getNoteId(), "text updated");

        ResultActions resultAction = mockMvc.perform(post("/patients/{id}/history/{noteId}", history.getPatientId(), observation.getNoteId(), observation.getNote())
                .contentType(MediaType.APPLICATION_JSON));

        History response = responseToObject(resultAction, History.class);

        assertThat(response)
                .isNotNull()
                .satisfies(r -> {
                    assertThat(r.getPatientId()).isEqualTo(1);
                    assertThat(r.getObservations()
                            .stream()
                            .filter(note -> note.getNoteId().equals(observation.getNoteId()))
                            .findFirst().get().getNoteId()).isEqualTo(1L);
                });

    }

    private <T> T responseToObject(ResultActions resultAction, Class<T> objectClass) throws Exception {
        return objectMapper.readValue(resultAction.andReturn().getResponse().getContentAsString(), objectClass);
    }

    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.info("User Dockerized MongoDb : " + mongoDbContainer.getContainerIpAddress() + ":" + mongoDbContainer.getPort());

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbContainer.getPort()

            );
            values.applyTo(configurableApplicationContext);
        }
    }
}

