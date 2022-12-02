package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService, PatientDao patientDao) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/patients")
    public List<Patient> allPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/patients/edit")
    public Patient formUpdatePatient(@RequestParam Integer id) {
       return patientService.findById(id);
    }

    @PutMapping("/patients/edit")
    public String updatePatient(@RequestParam Integer id, @RequestBody Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:patients";
    }

}
