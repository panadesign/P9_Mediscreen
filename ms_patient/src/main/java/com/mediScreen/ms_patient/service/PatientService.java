package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getPatients() {
        return patientDao.findAll();
    }

    public Optional<Patient> getPatientById(int id) {
        return patientDao.findById(id);
    }

//    public Optional<Patient> getPatientsByFamily(String family) {
//        List<Patient> getPatients = patientDao.findAll();
//        for(Patient patient : getPatients) {
//            if(patient.getFamily().equals(family)) {
//                return List<Patient>
//            }
//        }
//        return patientDao.findById(family);
//    }

}
