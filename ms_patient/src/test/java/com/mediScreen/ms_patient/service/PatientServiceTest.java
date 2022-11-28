package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private PatientService patientService;
    @Mock
    private PatientDao mockPatientDao;

    @BeforeEach
    public void init() {
        patientService = new PatientService(mockPatientDao);
    }

    @Test
    void getPatients() {
        //GIVEN
        List<Patient> allPatients = new ArrayList<>();

        allPatients.add( new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345"));
        allPatients.add( new Patient(2, "Lastname2", "Firstname2", Date.from(Instant.now()), "F", "Address2", "67890"));


        //WHEN
        when(mockPatientDao.findAll()).thenReturn(allPatients);
        List<Patient> patients = patientService.getPatients();

        //THEN
        Assertions.assertEquals(2, patients.size());
    }

    @Test
    void getPatientById() {
        //GIVEN
        Patient patient = new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345");

        //WHEN
        when(mockPatientDao.findById(1)).thenReturn(Optional.of(patient));
        Optional<Patient> patientToFind = patientService.getPatientById(patient.getId());

        //THEN
        Assertions.assertTrue(patientToFind.isPresent());
    }
}