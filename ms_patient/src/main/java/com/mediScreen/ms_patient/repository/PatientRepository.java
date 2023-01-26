package com.mediScreen.ms_patient.repository;

import com.mediScreen.ms_patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByLastname(String lastname);
}
