package com.mediScreen.ms_patient.dao;

import com.mediScreen.ms_patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {
    List<Patient> findByLastname(String lastname);
}
