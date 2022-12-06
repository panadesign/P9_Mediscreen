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
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        List<PatientBean> patients = microServicePatientProxy.allPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/patients/edit")
    public String formUpdatePatient(Model model, @RequestParam Integer id) {
        PatientBean patientBean = microServicePatientProxy.formUpdatePatient(id).get();
        model.addAttribute("patient", patientBean);
        log.debug("Access to patient update form");
        return "patientUpdate";
    }

    @PostMapping("/patients/edit")
    public String updatePatient(Model model, @RequestParam Integer id, PatientBean patientBean, BindingResult result) {

        if (result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "patientUpdate";
        }

        microServicePatientProxy.updatePatient(id, patientBean);

        model.addAttribute("patients", microServicePatientProxy.allPatients());
        log.debug("Patient : " + patientBean.getLastname() + " " + patientBean.getFirstname() + " is updated.");
        return "patients";
    }

    @GetMapping("/patients/add")
    public String formAddPatient(Model model, PatientBean patientBean) {
        log.debug("Access to patient add form");
        return "patientAdd";
    }

    @PostMapping("/patients/validate")
    public String addPatient(Model model, PatientBean patientBean, BindingResult result) {
        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "patients/add";
        }

        microServicePatientProxy.addPatient(patientBean);
        model.addAttribute("patients", microServicePatientProxy.allPatients());

        return "patients";
    }


}
