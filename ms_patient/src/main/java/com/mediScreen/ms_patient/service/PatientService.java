package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getPatients();
    Optional<Patient> findById(Integer id);
    Patient update(Integer id, Patient patient);
    Patient add(Patient patient);
    Patient findByLastname(String lastname);
}
