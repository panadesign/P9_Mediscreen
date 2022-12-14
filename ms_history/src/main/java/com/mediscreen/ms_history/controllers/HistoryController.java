package com.mediscreen.ms_history.controllers;

import com.mediscreen.ms_history.domain.History;
import com.mediscreen.ms_history.proxies.MicroServicePatientProxy;
import com.mediscreen.ms_history.service.HistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
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


//    http://localhost:8082/patHistory/add?patId=66666&e=Patient_Note

//    @PostMapping("/add")
//    public History add(@RequestParam("id") Integer id,
//                       @RequestParam("e") String note) {
//
//        microServicePatientProxy.getPatientById(id);
//
//        //Vérifier que le patient existe
//        return historyService.addObservation(id, note);
//
//        PatientBean patientBean = microServicePatientProxy.getPatientById(id);
//        patientBean.addObservation(note);
//
//    }

}
