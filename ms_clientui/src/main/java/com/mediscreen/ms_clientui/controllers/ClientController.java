package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    private final MicroServicePatientProxy microServicePatientProxy;

    public ClientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @RequestMapping("/patients")
    public String accueil(Model model) {
        List<PatientBean> patients = microServicePatientProxy.allPatients();
        model.addAttribute("patients", patients);
        return "accueil";
    }


    @RequestMapping("/patients/{lastname}")
    public String patient(Model model, @PathVariable("lastname") String lastname) {
        List<PatientBean> patients = microServicePatientProxy.patientsByLastname(lastname);
        model.addAttribute("patients", patients);
        return "patient";
    }

    @RequestMapping("/patients/update/{id}")
    public String updateFormPatient(Model model, @PathVariable("id") Integer id) {
        PatientBean patientBean = microServicePatientProxy.updateFormPatient(id).get();
        model.addAttribute("patient", patientBean);
        return "patientUpdate";
    }



}
