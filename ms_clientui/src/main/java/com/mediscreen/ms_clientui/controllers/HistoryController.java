package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.HistoryBean;
import com.mediscreen.ms_clientui.beans.NoteBean;
import com.mediscreen.ms_clientui.proxies.MicroServiceHistoryProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Log4j2
@Controller
public class HistoryController {

    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    public HistoryController(MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    @GetMapping("/patients/{id}/history")
    public String get(Model model, @PathVariable("id") Integer id) {
        log.debug("Access to patient's all notes");
        HistoryBean history = microServiceHistoryProxy.get(id);

        model.addAttribute("history", history);
        return "history/patientHistory";
    }

    @GetMapping("/patients/{id}/history/add")
    public String addForm(Model model, @PathVariable("id") Integer id) {
        log.debug("Access to adding form for a new note");
        model.addAttribute("id", id);
        model.addAttribute("noteBean", new NoteBean());
        return "history/historyAdd";
    }


    @PostMapping("/patients/{id}/history/add")
    public String add(Model model, @PathVariable("id") Integer id, @Valid NoteBean note, BindingResult result) {
        log.debug("Add note for patient with id " + id);

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "history/historyAdd";
        }

        HistoryBean history = microServiceHistoryProxy.add(id, note.getNote());
        model.addAttribute("history", history);

        return "redirect:/patHistory?id=" + id;
    }
}

