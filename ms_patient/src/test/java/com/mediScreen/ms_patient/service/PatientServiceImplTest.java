package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.controllers.PatientController;
import com.mediScreen.ms_patient.dao.PatientDao;
import com.mediScreen.ms_patient.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
class PatientServiceImplTest {

    private PatientService patientServiceImpl;
    @MockBean
    private PatientDao mockPatientDao;

    @BeforeEach
    public void init() {
        patientServiceImpl = new PatientServiceImpl(mockPatientDao);
    }

    @Test
    void getPatients() {
//        //GIVEN
//        List<Patient> allPatients = new ArrayList<>();
//
//        allPatients.add(new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345"));
//        allPatients.add(new Patient(2, "Lastname2", "Firstname2", Date.from(Instant.now()), "F", "Address2", "67890"));
//
//
//        //WHEN
//        when(mockPatientDao.findAll()).thenReturn(allPatients);
//        List<Patient> patients = patientServiceImpl.getPatients();
//
//        //THEN
//        Assertions.assertEquals(2, patients.size());
    }

    @Test
    void getPatientByLastName() {
        //GIVEN
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345");
        patients.add(patient);

        //WHEN
        when(mockPatientDao.findByLastname("Lastname")).thenReturn(patients);
        List<Patient> allPatients = patientServiceImpl.getPatientsByLastname(patient.getLastname());

        //THEN
        Assertions.assertEquals(1, allPatients.size());
    }

    @Test
    void updatePatient() {
        //GIVEN
//        Patient patient = new Patient(1, "Lastname", "Firstname", Date.from(Instant.now()), "M", "Address", "12345");
//
//        //WHEN
//        when(mockPatientDao.findById(patient.getId())).thenReturn(Optional.of(patient));
//
//        patient.setAddress("Address modified");
//
//        Patient patientUpdated = patientServiceImpl.updatePatient(patient);
//
//        //THEN
//        Assertions.assertEquals("Address modified", patientUpdated.getAddress());


    }
}