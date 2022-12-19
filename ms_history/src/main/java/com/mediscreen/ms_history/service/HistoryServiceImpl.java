package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.exceptions.ResourceNotExistingException;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;
    private final MicroServicePatientProxy microServicePatientProxy;

    public HistoryServiceImpl(HistoryRepository historyRepository, MicroServicePatientProxy microServicePatientProxy) {
        this.historyRepository = historyRepository;
        this.microServicePatientProxy = microServicePatientProxy;
    }

    public History getHistoryById(Integer id) {
        return historyRepository.findById(id).orElseThrow(() -> new ResourceNotExistingException("Patient's history with id " + id + " doesn't exist."));
    }

    public History addObservation(Integer id, String note) {

        if(historyRepository.findById(id).isPresent()) {
            History history = getHistoryById(id);
            history.addObservation(note);
            return historyRepository.save(history);
        }

        else {
            History history = new History(id, new ArrayList<>());
            history.addObservation(note);
            return historyRepository.save(history);
        }
    }
}
