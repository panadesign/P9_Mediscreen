package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.exceptions.ResourceNotExistingException;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;


    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;

    }

    public History getHistoryById(Integer id)  throws ResourceNotExistingException{
        return historyRepository.findById(id).orElse(new History(id));
    }

    public History addObservation(Integer id, String note) throws ResourceNotExistingException {
        History history = getHistoryById(id);
        history.addObservation(note);
        return historyRepository.save(history);
    }
}
