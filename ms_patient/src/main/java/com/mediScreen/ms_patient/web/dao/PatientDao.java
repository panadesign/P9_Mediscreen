package com.mediScreen.ms_patient.web.dao;

import com.mediScreen.ms_patient.web.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {
}
