package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration;

import java.util.List;

public interface PatientService {
    List<Patient> getPatients();
    Patient findById(Integer id);
    void updatePatient(Integer id, Patient patient);
}
