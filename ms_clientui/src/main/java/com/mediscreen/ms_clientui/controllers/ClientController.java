package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/patients/{id}")
    public String patient(Model model, @PathVariable("id") Integer id) {
        Optional<PatientBean> patientOptional = microServicePatientProxy.patientById(id);
        PatientBean patient = patientOptional.stream().map(patientBean -> new PatientBean(patientBean.getId(), patientBean.getFamily(), patientBean.getGiven(), patientBean.getBirth(), patientBean.getGender(), patientBean.getAddress(), patientBean.getPhone()))
                        .findAny().get();
        model.addAttribute("patient", patient);
        return "patient";
    }

}
