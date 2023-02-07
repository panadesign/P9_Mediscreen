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

/**
 * The type History controller.
 */
@Log4j2
@Controller
public class HistoryController {

    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    /**
     * Instantiates a new History controller.
     *
     * @param microServiceHistoryProxy the micro service history proxy
     */
    public HistoryController(MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    /**
     * Get patient history.
     *
     * @param model the model
     * @param id    the id
     * @return the patient history
     */
    @GetMapping("/patients/{id}/history")
    public String get(Model model, @PathVariable("id") Integer id) {
        log.info("Access to patient's all notes");
        HistoryBean history = microServiceHistoryProxy.get(id);

        model.addAttribute("history", history);

        return "history/patientHistory";
    }

    /**
     * Get history add form
     *
     * @param model the model
     * @param id    the id
     * @return the form to add a new history
     */
    @GetMapping("/patients/{id}/history/add")
    public String addForm(Model model, @PathVariable("id") Integer id) {
        log.info("Access to adding form for a new note");
        model.addAttribute("id", id);
        model.addAttribute("noteBean", new NoteBean());
        return "history/historyAdd";
    }


    /**
     * Add a new note
     *
     * @param model  the model
     * @param id     the id
     * @param note   the note
     * @param result the result
     * @return history of patient
     */
    @PostMapping("/patients/{id}/history/add")
    public String add(Model model, @PathVariable("id") Integer id, @Valid NoteBean note, BindingResult result) {
        log.info("Add note for patient with id " + id);

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "history/historyAdd";
        }

        HistoryBean history = microServiceHistoryProxy.add(id, note.getNote());
        model.addAttribute("history", history);
        log.info("A new note for patient with id " + id + " has been added");
        return "redirect:/patients/{id}/history";
    }

    /**
     * Get update form for history
     * @param model
     * @param id
     * @return update form for history
     */
    @GetMapping("/patients/{id}/history/{noteId}")
    public String updateForm(Model model, @PathVariable("id") Integer id, @PathVariable("noteId")Long noteId, String note) {

        var observation = microServiceHistoryProxy.get(id).getObservations()
                .stream()
                .filter(n -> n.getNoteId().equals(noteId))
                .findFirst();

        model.addAttribute("observation", observation);
        log.info("Access to updating form for a note");
        return "history/historyUpdate";
    }

    @PostMapping("/patients/{id}/history/{noteId}")
    public String updateHistory(Model model, @PathVariable("id") Integer id, @PathVariable("noteId")Long noteId, String note, @Valid NoteBean noteBean, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "redirect:/patients/{id}/history/{noteId}";
        }

        microServiceHistoryProxy.update(id, noteId, note);
        model.addAttribute("observation", microServiceHistoryProxy.get(id));

        log.info("Note with id " + noteId + " has been updated whit this note " + note);
        return "redirect:/patients/{id}/history";
    }
}