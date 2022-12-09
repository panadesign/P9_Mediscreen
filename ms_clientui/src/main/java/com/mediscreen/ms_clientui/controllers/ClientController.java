package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class ClientController {

    private final MicroServicePatientProxy microServicePatientProxy;
    public ClientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<PatientBean> patients = microServicePatientProxy.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/patients/{id}")
    public String getPatientById(Model model, @PathVariable("id") Integer id) {

        log.debug("Access update form to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);

        return "patient";
    }

    @GetMapping("/patients/{id}/edit")
    public String formUpdatePatient(Model model, @PathVariable("id") Integer id) {

        log.debug("Access update form to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patientUpdate";
    }

    @PostMapping("/patients/{id}/edit")
    public String updatePatient(Model model, @PathVariable("id") Integer id, @Valid PatientBean patientBean, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "redirect:/patients/{id}/edit";
        }

        microServicePatientProxy.updatePatient(id, patientBean);
        model.addAttribute("patient", microServicePatientProxy.getAllPatients());

        log.debug("Patient : " + patientBean.getLastname() + " " + patientBean.getFirstname() + " is updated.");
        return "redirect:/patients";
    }

    @GetMapping("/patients/add")
    public String formAddPatient(Model model, PatientBean patientBean) {
        log.debug("Access to patient add form");
        return "patientAdd";
    }

    @PostMapping("/patients/validate")
    public String addPatient(Model model, @Valid PatientBean patientBean, BindingResult result) {
        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "patientAdd";
        }

        microServicePatientProxy.addPatient(patientBean);
        model.addAttribute("patients", microServicePatientProxy.getAllPatients());

        log.debug("Patient : " + patientBean.getLastname() + patientBean.getFirstname() + " is created.");
        return "redirect:/patients";
    }


}
