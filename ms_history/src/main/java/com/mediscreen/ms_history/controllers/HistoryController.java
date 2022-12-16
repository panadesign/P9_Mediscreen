package com.mediscreen.ms_history.controllers;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class HistoryController {

    private final HistoryService historyService;
    private final MicroServicePatientProxy microServicePatientProxy;


    public HistoryController(HistoryService historyService, MicroServicePatientProxy microServicePatientProxy) {
        this.historyService = historyService;
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @GetMapping("/patHistory")
    public History get(@PathVariable Integer id) {
        return historyService.getHistoryById(id);
    }


//    http://localhost:8082/patHistory/add?patId=66666&e=Patient_Note

    @PostMapping("/patHistory/add")
    public History add(@RequestParam("id") Integer patId,
                       @RequestParam("e") String note) {

        //Vérifier que le patient existe
        return historyService.addObservation(patId, note);

    }

}
