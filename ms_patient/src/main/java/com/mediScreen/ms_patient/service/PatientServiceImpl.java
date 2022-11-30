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

    public Patient findById(Integer id) {
        return patientDao.findById(id).orElseThrow(()-> new RessourceNotExistingException("Patient with id " + id + " doesn't exist."));
    }

    public void updatePatient(Patient patient) {
        Patient patientToUpdate = findById(patient.getId());
        patientToUpdate.setLastname(patient.getLastname());
        patientToUpdate.setFirstname(patient.getFirstname());
        patientToUpdate.setBirth(patient.getBirth());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());
        patientDao.save(patientToUpdate);
    }

}
