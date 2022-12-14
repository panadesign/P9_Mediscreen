package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.exceptions.ResourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getPatients() {
        return patientDao.findAll();
    }

    public Patient findById(Integer id) {
        return patientDao.findById(id).orElseThrow(() -> new ResourceNotExistingException("Patient with id " + id + " doesn't exist."));
    }

    public Patient update(Integer id, Patient patient) {
        Patient patientToUpdate = findById(id);

        patientToUpdate.setLastname(patient.getLastname());
        patientToUpdate.setFirstname(patient.getFirstname());
        patientToUpdate.setBirth(patient.getBirth());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());

        return patientDao.save(patientToUpdate);

    }

    public Patient add(Patient patient) {
        log.debug("Create a new patient with id: " + patient.getId());
        return patientDao.save(patient);
    }

    public boolean patientExist(Integer id) {

        return true;
    }

}
