package com.mediScreen.ms_patient.repository;

import com.mediScreen.ms_patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Patient repository.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    /**
     * Find by lastname optional.
     *
     * @param lastname the lastname
     * @return the optional
     */
    Optional<Patient> findByLastname(String lastname);
}
