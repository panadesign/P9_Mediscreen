package com.mediScreen.ms_patient.service;

import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PatientServiceImplTest {

    private PatientServiceImpl patientServiceImpl;
    @Mock
    private PatientRepository mockPatientRepository;

    @BeforeEach
    void init() {
        patientServiceImpl = new PatientServiceImpl(mockPatientRepository);
    }

    @Test
    void getPatients() {
        //GIVEN
        List<Patient> allPatients = new ArrayList<>();
        LocalDate dateTime = LocalDate.now();

        allPatients.add(new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345"));
        allPatients.add(new Patient(2, "Lastname2", "Firstname2", dateTime, "F", "Address2", "67890"));

        //WHEN
        when(mockPatientRepository.findAll()).thenReturn(allPatients);
        List<Patient> patients = patientServiceImpl.getPatients();

        //THEN
        Assertions.assertEquals(2, patients.size());
    }

    @Test
    void getPatientById() {
        LocalDate dateTime = LocalDate.now();
        Patient patient1 = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        when(mockPatientRepository.findById(1)).thenReturn(Optional.of(patient1));

        Optional<Patient> patient = patientServiceImpl.findById(patient1.getId());

        assertThat(patient)
                .isNotEmpty()
                .get()
                .satisfies(p -> assertThat(p.getLastname()).isEqualTo(patient1.getLastname()));
    }

    @Test
    void getPatientByLastname() {
        LocalDate dateTime = LocalDate.now();
        Patient patient1 = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        when(mockPatientRepository.findByLastname("Lastname")).thenReturn(List.of(patient1));

        List<Patient> patient = patientServiceImpl.findByLastname(patient1.getLastname());

        assertThat(patient)
                .isNotEmpty()
                .satisfies(p -> assertThat(p.contains(patient1)).isTrue());

    }

    @Test
    void updatePatient() {
        //GIVEN
        LocalDate dateTime = LocalDate.now();
        Patient patient = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        //WHEN
        when(mockPatientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        patient.setAddress("Address updated");

        patientServiceImpl.update(patient.getId(), patient);

        //THEN
        Assertions.assertEquals("Address updated", patient.getAddress());
    }

    @Test
    void addNewPatient() {
        //GIVEN
        LocalDate dateTime = LocalDate.now();
        Patient patient = new Patient(1, "Lastname", "Firstname", dateTime, "M", "Address", "12345");

        //WHEN
        when(mockPatientRepository.save(patient)).thenAnswer(p -> p.getArguments()[0]);
        Patient patientToAdd = patientServiceImpl.add(patient);

        //THEN
        assertThat(patientToAdd)
                .satisfies(p -> {
                            assertThat(patient.getId()).hasToString("1");
                            assertThat(patient.getLastname()).hasToString("Lastname");
                            assertThat(patient.getFirstname()).hasToString("Firstname");
                            assertThat(patient.getGender()).hasToString("M");
                            assertThat(patient.getAddress()).hasToString("Address");
                            assertThat(patient.getPhone()).hasToString("12345");
                        }
                );

    }
}