package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientDao patientDao;

    public Iterable<Patient> getPatients() {
        return patientDao.findAll();
    }

}
