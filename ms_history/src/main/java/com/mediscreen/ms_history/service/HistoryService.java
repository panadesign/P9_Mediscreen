package com.mediscreen.ms_history.service;


import com.mediscreen.ms_history.domain.History;

/**
 * The interface History service.
 */
public interface HistoryService {
    /**
     * Gets history by id.
     *
     * @param id the id
     * @return the history by id
     */
    History getHistoryById(Integer id);

    /**
     * Add observation history.
     *
     * @param id   the id
     * @param note the note
     * @return the history
     */
    History addObservation(Integer id, String note);
}
