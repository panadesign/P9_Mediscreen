package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.exceptions.ResourceNotExistingException;
import com.mediscreen.ms_history.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History getHistoryById(Integer id) {
        return historyRepository.findById(id).orElseThrow(() -> new ResourceNotExistingException("Patient's history with id " + id + " doesn't exist."));
    }

    public History addObservation(Integer id, String note) {

        return historyRepository.save(new History());

    }
}
