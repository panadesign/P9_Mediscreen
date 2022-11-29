package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.exceptions.RessourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getPatients() {
        return patientDao.findAll();
    }

    public List<Patient> getPatientsByLastname(String lastname) {
        return patientDao.findByLastname(lastname);
    }

    public Patient updatePatient(Integer id) {
        Patient patientToUpdate = patientDao.findById(id)
                .orElseThrow(() -> new RessourceNotExistingException("This patient doesn't exist"));


        return patientDao.save(patientToUpdate);
    }

}
