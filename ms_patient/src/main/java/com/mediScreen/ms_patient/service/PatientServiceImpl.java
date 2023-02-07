package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.exceptions.ResourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.repository.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
/**
 * Patient service is used to <b>get</b>, <b>create</b> and <b>update</b> patients
 * @exception methods can throw ResourceNotExistingException
 */
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    /**
     * Instantiates a new Patient service.
     *
     * @param patientRepository the patient repository
     */
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Method to get all patients
     * @return return a patient list
     */
    public List<Patient> getPatients() {
        log.info("Get all patients");
        return patientRepository.findAll();
    }

    /**
     * Get Patient by id number
     * @param id unique identifier for a patient
     * @return return a patient by his id
     */
    public Optional<Patient> findById(Integer id) {
        log.info("Get patient with id: " + id);
        Optional<Patient> patient = patientRepository.findById(id);

        return Optional.ofNullable(patient.orElseThrow(() -> new ResourceNotExistingException("Patient with id " + id + " doesn't exist")));
    }

    /**
     * Get patient by his lastname
     * @param lastname of the patient
     * @return return a patient by his lastname
     */
    public Optional<Patient> findByLastname(String lastname) {
        log.info("Get patient with lastname: " + lastname);
        Optional<Patient> patient = patientRepository.findByLastname(lastname.toLowerCase());

        return Optional.ofNullable(patient.orElseThrow(() -> new ResourceNotExistingException("Patient with lastname " + lastname + " doesn't exist")));
    }

    /**
     * Update a patient
     * @param id is used to find patient to update
     * @param patient expect a patient
     * @return return a patient updated in database
     */
    public Patient update(Integer id, Patient patient) {
        Optional<Patient> patientToUpdate = findById(id);

        patientToUpdate.get().setLastname(patient.getLastname());
        patientToUpdate.get().setFirstname(patient.getFirstname());
        patientToUpdate.get().setBirth(patient.getBirth());
        patientToUpdate.get().setGender(patient.getGender());
        patientToUpdate.get().setAddress(patient.getAddress());
        patientToUpdate.get().setPhone(patient.getPhone());

        log.info("Update patient with id: " + id);
        return patientRepository.save(patientToUpdate.get());
    }

    /**
     * Create a new patient
     * @param patient expect a patient
     * @return return a new patient save in database
     */
    public Patient add(Patient patient) {
        log.info("Create a new patient");
        return patientRepository.save(patient);
    }

}
