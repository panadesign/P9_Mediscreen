package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Log4j2
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
        log.debug("Access to update form for patient with id: " + id);
        return patientService.findById(id);
    }

    @PostMapping("/patients/edit")
    public void updatePatient(@RequestParam Integer id, Patient patient) {
        log.debug("Patient : " + patient.getLastname() + " " + patient.getFirstname() + " is updated.");
        patientService.updatePatient(id, patient);
    }

}
