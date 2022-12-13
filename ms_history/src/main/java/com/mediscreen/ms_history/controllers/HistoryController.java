package com.mediscreen.ms_history.controllers;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/{id}")
    public History getHistoryById(@PathVariable("id") String id) {
        log.debug("Access to patient's history with id: " + id);
        return historyService.getHistoryById(id);
    }

}
