package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatients();
    Patient findById(Integer id);
    Patient update(Integer id, Patient patient);
    Patient add(Patient patient);
    boolean patientExist(Integer id);
}
