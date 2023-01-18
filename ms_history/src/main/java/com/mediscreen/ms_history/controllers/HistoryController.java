package com.mediscreen.ms_history.controllers;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class HistoryController {

    private final HistoryService historyService;


    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/patients/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public History get(@PathVariable Integer id) {
        return historyService.getHistoryById(id);
    }

    @PostMapping("/patients/{id}/history/add")
    @ResponseStatus(HttpStatus.CREATED)
    public History add(@PathVariable("id") Integer patId,
                       @RequestParam("e") String note) {

        return historyService.addObservation(patId, note);
    }

}
