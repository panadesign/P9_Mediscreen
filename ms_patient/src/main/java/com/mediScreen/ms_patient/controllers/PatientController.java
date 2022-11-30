package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    private final PatientService patientService;
    private final PatientDao patientDao;
    public PatientController(PatientService patientService, PatientDao patientDao) {
        this.patientService = patientService;
        this.patientDao = patientDao;
    }

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> allPatients() {
        return patientService.getPatients();
    }

    @GetMapping("patients/{id}/edit")
    public Optional<Patient> updateFormPatient(@RequestParam("id") Integer id) {
        return patientDao.findById(id);
    }
}
