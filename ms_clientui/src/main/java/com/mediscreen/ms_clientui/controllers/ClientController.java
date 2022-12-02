package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
        return "patientUpdate";
    }

    @PutMapping("patients/edit")
    public String updatePatient(Model model, @RequestParam Integer id, @RequestBody PatientBean patientBean) {
        microServicePatientProxy.updatePatient(id, patientBean);
        model.addAttribute("patient", patientBean);
        return "redirect:patients";
    }


}
