package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
public class ClientController {

    private final MicroServicePatientProxy microServicePatientProxy;
    public ClientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @RequestMapping("/")
    public String accueil() {
        return "accueil";
    }

    @RequestMapping("/patients")
    public String patients(Model model) {
        List<PatientBean> patients = microServicePatientProxy.allPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @RequestMapping("/patients/edit")
    public String formUpdatePatient(Model model, @RequestParam Integer id) {
        PatientBean patientBean = microServicePatientProxy.formUpdatePatient(id).get();
        model.addAttribute("patient", patientBean);
        log.debug("Access to patient update form");
        return "patientUpdate";
    }

    @PostMapping("patients/edit")
    public String updatePatient(Model model, @RequestParam Integer id, PatientBean patientBean) {
        microServicePatientProxy.updatePatient(id, patientBean);
        model.addAttribute("patients", patientBean);
        log.debug("Patient : " + patientBean.getLastname() + " " + patientBean.getFirstname() + " is updated.");
        log.debug("Patient is updated");
        return "patients";
    }


}
