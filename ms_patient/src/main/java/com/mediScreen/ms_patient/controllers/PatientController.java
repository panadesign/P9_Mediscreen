package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.service.PatientService;
import com.mediScreen.ms_patient.web.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
        public Iterable<Patient> allPatients() {
            return patientService.getPatients();
        }

}
