package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatients();
    List<Patient> getPatientsByLastname(String lastname);
    Patient updatePatient(Integer id);
}
