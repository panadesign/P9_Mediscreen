package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServiceHistoryProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Log4j2
@Controller
public class HistoryController {

    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    public HistoryController(MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    @GetMapping("/patHistory")
    public String get(Model model, @RequestParam("id") Integer id) {
        log.debug("Access to patient's all notes");
        HistoryBean history = microServiceHistoryProxy.get(id);

        model.addAttribute("history", history);
        return "history/patientHistory";
    }

    @GetMapping("/patHistory/add")
    public String addForm(@RequestParam("id") Integer id) {
        log.debug("Access to adding form for a new note");
        microServiceHistoryProxy.get(id);
        return "history/historyAdd";
    }


    @PostMapping("/patHistory/add")
    public String add(Model model, @RequestParam("id") Integer patId, @RequestParam("e") String note, BindingResult result) {
        log.debug("Add note for patient with id " + patId);
        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "history/historyAdd";
        }
        HistoryBean history = microServiceHistoryProxy.add(patId, note);
        model.addAttribute("history", history);

        return "redirect:/history/patientHistory";
    }
}

