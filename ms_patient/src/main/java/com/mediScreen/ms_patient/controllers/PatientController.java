package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.service.PatientService;
import com.mediScreen.ms_patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
