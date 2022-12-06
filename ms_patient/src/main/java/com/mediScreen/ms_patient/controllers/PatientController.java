package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/patients")
    public List<Patient> allPatients() {
        log.debug("Get all patients");
        return patientService.getPatients();
    }

    @GetMapping("/patients/edit")
    public Patient formUpdatePatient(@RequestParam Integer id) {
        log.debug("Access to update form for patient with id: " + id);
        return patientService.findById(id);
    }

    @PutMapping("/patients/edit")
    public Patient updatePatient(@RequestParam Integer id, @RequestBody Patient patient) {
        log.debug("Patient : " + patient.getLastname() + " " + patient.getFirstname() + " is updated.");
        return patientService.update(id, patient);
    }

    @GetMapping("/patients/add")
    public String addPatientForm(Patient patient) {
        log.debug("Get add patient form");
        return "patientAdd";
    }

    @PostMapping("/patients/validate")
    public String addPatient(@RequestBody @Valid Patient patient) {
        patientService.add(patient);
        return "redirect:patients";
    }



}
