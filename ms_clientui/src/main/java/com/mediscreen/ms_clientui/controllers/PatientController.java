package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/patients")
public class PatientController {

    private final MicroServicePatientProxy microServicePatientProxy;

    public PatientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @GetMapping()
    public String getAllPatients(Model model) {
        List<PatientBean> patients = microServicePatientProxy.getAllPatients();
        model.addAttribute("patients", patients);
        log.info("Get all patients");
        return "patient/patients";
    }

    @GetMapping("/{id}")
    public String getPatientById(Model model, @PathVariable("id") Integer id) {

        log.info("Access to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);

        return "patient/patient";
    }

    @GetMapping("/{id}/edit")
    public String formUpdatePatient(Model model, @PathVariable("id") Integer id) {

        log.info("Access update form to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/patientUpdate";
    }

    @PostMapping("/{id}/edit")
    public String updatePatient(Model model, @PathVariable("id") Integer id, @Valid PatientBean patientBean, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "redirect:/patients/{id}/edit";
        }

        microServicePatientProxy.updatePatient(id, patientBean);
        model.addAttribute("patient", microServicePatientProxy.getAllPatients());

        log.info("Patient : " + patientBean.getLastname() + " " + patientBean.getFirstname() + " is updated.");
        return "redirect:/patients";
    }

    @GetMapping("/add")
    public String formAddPatient(Model model, PatientBean patientBean) {
        log.info("Access to patient add form");
        return "patient/patientAdd";
    }

    @PostMapping()
    public String add(Model model, @Valid PatientBean patientBean, BindingResult result) {
        log.info("Add an new patient");
        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "patient/patientAdd";
        }

        microServicePatientProxy.addPatient(patientBean);
        model.addAttribute("patients", microServicePatientProxy.getAllPatients());

        log.info("Patient : " + patientBean.getLastname() + patientBean.getFirstname() + " is created.");
        return "redirect:/patients";
    }
}
