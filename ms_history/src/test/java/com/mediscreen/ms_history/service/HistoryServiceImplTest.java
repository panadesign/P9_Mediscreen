package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.repository.HistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class HistoryServiceImplTest {

    private HistoryService historyServiceImpl;

    @Mock
    HistoryRepository historyRepository;

    @BeforeEach
    void init() {
        historyServiceImpl = new HistoryServiceImpl(historyRepository);
    }

    @Test
    void getHistoryById() {
        //GIVEN
        History history = new History(1, new ArrayList<>());

        //WHEN
        when(historyRepository.findById(any())).thenReturn(Optional.of(history));
        History history1 = historyServiceImpl.getHistoryById(1);

        //THEN
        Assertions.assertEquals(1, history1.getPatientId());
    }

    @Test
    void addObservation() {
        //GIVEN
        History history = new History(1, new ArrayList<>());

        //WHEN
        when(historyRepository.save(history)).thenAnswer(h -> h.getArguments()[0]);
        History noteToAdd = historyServiceImpl.addObservation(1, "new note");

        //THEN
        assertThat(noteToAdd)
                .satisfies(p -> {
                            assertThat(history.getPatientId()).hasToString("1");
                            assertThat(history.getObservations().size()).isNotNull();
                        }
                );
    }

    @Test
    void updateHistory() {
        //GIVEN
        History.Observation observation = new History.Observation(12L, "test", LocalDate.now());

        List<History.Observation> observations = new ArrayList<>();
        observations.add(observation);

        History history = new History(1, observations);

        //WHEN
        when(historyRepository.findById(1)).thenReturn(Optional.of(history));
        when(historyRepository.save(history)).thenReturn(history);
        History historyToUpdate = historyServiceImpl.updateObservation(1, 12L, "testUpdated");

        //THEN
        Assertions.assertTrue(historyToUpdate.getObservations().get(0).getNote().equals(observation.getNote()));
    }
}