package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;

import java.util.List;
import java.util.Optional;

/**
 * The interface Patient service.
 */
public interface PatientService {
    /**
     * Gets patients.
     *
     * @return all patients
     */
    List<Patient> getPatients();

    /**
     * Find by id optional of patient.
     *
     * @param id the id
     * @return the optional of patient
     */
    Optional<Patient> findById(Integer id);

    /**
     * Find by lastname optional of patient.
     *
     * @param lastname the lastname
     * @return the optional of patient
     */
    Optional<Patient> findByLastname(String lastname);

    /**
     * Update patient.
     *
     * @param id      the id
     * @param patient the patient
     * @return the patient updated
     */
    Patient update(Integer id, Patient patient);

    /**
     * Add patient.
     *
     * @param patient the patient
     * @return the new patient
     */
    Patient add(Patient patient);
}
