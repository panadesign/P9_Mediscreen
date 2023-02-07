package com.mediscreen.ms_history.controllers;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The type History controller.
 */
@Log4j2
@RestController
public class HistoryController {

    private final HistoryService historyService;


    /**
     * Instantiates a new History controller.
     *
     * @param historyService the history service
     */
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Get history by id
     *
     * @param id the patient id
     * @return the history
     */
    @GetMapping("/patients/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public History get(@PathVariable Integer id) {
        return historyService.getHistoryById(id);
    }

    /**
     * Add history.
     *
     * @param patId the pat id
     * @param note  the note to add
     * @return a new history
     */
    @PostMapping("/patients/{id}/history/add")
    @ResponseStatus(HttpStatus.CREATED)
    public History add(@PathVariable("id") Integer patId,
                       @RequestParam("note") String note) {
        log.debug("Add a new note to patient with id " + patId);
        return historyService.addObservation(patId, note);
    }

    @PostMapping("/patients/{id}/history/{noteId}")
    public History update(@PathVariable("id") Integer id, @PathVariable("noteId") Long noteId, String note) {
        log.info("History of patient with id " + id + " is updated");
        return historyService.updateObservation(id, noteId, note);
    }

}
