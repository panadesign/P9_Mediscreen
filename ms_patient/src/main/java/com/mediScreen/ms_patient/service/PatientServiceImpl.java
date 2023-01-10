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
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Integer id) {
        return patientRepository.findById(id);
    }

    public Patient update(Integer id, Patient patient) {
        Optional<Patient> patientToUpdate = findById(id);

        patientToUpdate.get().setLastname(patient.getLastname());
        patientToUpdate.get().setFirstname(patient.getFirstname());
        patientToUpdate.get().setBirth(patient.getBirth());
        patientToUpdate.get().setGender(patient.getGender());
        patientToUpdate.get().setAddress(patient.getAddress());
        patientToUpdate.get().setPhone(patient.getPhone());

        return patientRepository.save(patientToUpdate.get());
    }

    public Patient add(Patient patient) {
        log.debug("Create a new patient with id: " + patient.getId());
        return patientRepository.save(patient);
    }

}
