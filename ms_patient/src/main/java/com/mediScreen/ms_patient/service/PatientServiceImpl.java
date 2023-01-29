package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.repository.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        log.debug("Get all patients");
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Integer id) {
        log.debug("Get patient with id: " + id);
        return patientRepository.findById(id);
    }

    public Patient findByLastname(String lastname) {
        log.debug("Get patient with lastname: " + lastname);
        return patientRepository.findByLastname(lastname);
    }

    public Patient update(Integer id, Patient patient) {
        Optional<Patient> patientToUpdate = findById(id);

        patientToUpdate.get().setLastname(patient.getLastname());
        patientToUpdate.get().setFirstname(patient.getFirstname());
        patientToUpdate.get().setBirth(patient.getBirth());
        patientToUpdate.get().setGender(patient.getGender());
        patientToUpdate.get().setAddress(patient.getAddress());
        patientToUpdate.get().setPhone(patient.getPhone());

        log.debug("Update patient with id: " + id);
        return patientRepository.save(patientToUpdate.get());
    }

    public Patient add(Patient patient) {
        log.debug("Create a new patient with id: " + patient.getId());
        return patientRepository.save(patient);
    }

}
