package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.repository.PatientRepository;
import com.mediScreen.ms_patient.exceptions.ResourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotExistingException("Patient with id " + id + " doesn't exist."));
    }

    public Patient update(Integer id, Patient patient) {
        Patient patientToUpdate = findById(id);

        patientToUpdate.setLastname(patient.getLastname());
        patientToUpdate.setFirstname(patient.getFirstname());
        patientToUpdate.setBirth(patient.getBirth());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());

        return patientRepository.save(patientToUpdate);

    }

    public Patient add(Patient patient) {
        log.debug("Create a new patient with id: " + patient.getId());
        return patientRepository.save(patient);
    }


}
