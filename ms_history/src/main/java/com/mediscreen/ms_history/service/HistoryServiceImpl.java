package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        return historyRepository.findById(id).orElse(new History(id));
    }

    public History addObservation(Integer id, String note) {
        History history = getHistoryById(id);
        history.addObservation(note);
        return historyRepository.save(history);
    }
}
