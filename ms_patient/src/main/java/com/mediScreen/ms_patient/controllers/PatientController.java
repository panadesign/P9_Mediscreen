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

    private final PatientDao patientDao;
    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @GetMapping("/patients")
    public List<Patient> allPatients() {
        return patientDao.findAll();
    }

    @GetMapping("/patients/{lastname}")
    public List<Patient> patientsByLastname(@PathVariable("lastname") String lastname) {
        return patientDao.findByLastname(lastname);
    }

    @GetMapping("/patients/update/{id}")
    public Optional<Patient> updateFormPatient(@PathVariable("id") Integer id) {
        return patientDao.findById(id);
    }
}
