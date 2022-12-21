package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.repository.HistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class HistoryServiceImplTest {

    private HistoryService historyServiceImpl;

    @Mock
    HistoryRepository historyRepository;

    @Mock
    MicroServicePatientProxy microServicePatientProxy;

    @BeforeEach
    void init() {
        historyServiceImpl = new HistoryServiceImpl(historyRepository, microServicePatientProxy);
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
    }
}