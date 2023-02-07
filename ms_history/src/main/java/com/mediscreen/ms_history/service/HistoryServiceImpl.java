package com.mediscreen.ms_history.service;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.exceptions.ResourceNotExistingException;
import com.mediscreen.ms_history.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * The type History service.
 */
@Service
@Log4j2
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;


    /**
     * Instantiates a new History service.
     *
     * @param historyRepository the history repository
     */
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;

    }

    /**
     * Get history by id
     *
     * @param id of the history
     * @return history
     * @throws ResourceNotExistingException
     */
    public History getHistoryById(Integer id) throws ResourceNotExistingException {
        log.info("Get history with id:" + id);
        return historyRepository.findById(id).orElse(new History(id));
    }

    /**
     * Add a new observation
     *
     * @param id   of the history
     * @param note to add to the history
     * @return a new observation
     * @throws ResourceNotExistingException
     */
    public History addObservation(Integer id, String note) throws ResourceNotExistingException {
        History history = getHistoryById(id);
        history.addObservation(note);
        log.info("Add a new observation with note: " + note);
        return historyRepository.save(history);
    }

    @Override
    public History updateObservation(Integer id, Long noteId,  String note) throws ResourceNotExistingException {
        History history = getHistoryById(id)
                .updateObservation(noteId, note);
        return historyRepository.save(history);
    }
}
