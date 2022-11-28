package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {

    private final MicroServicePatientProxy microServicePatientProxy;

    public ClientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @RequestMapping("/")
    public String accueil(Model model) {
        List<PatientBean> patients = microServicePatientProxy.allPatients();
        model.addAttribute("patients", patients);
        return "accueil";
    }

}
