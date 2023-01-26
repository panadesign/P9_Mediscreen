package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.model.RiskLevel;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AssessmentServiceImplTest {

    private AssessmentServiceImpl assessmentServiceImpl;

    @Mock
    private MicroServicePatientProxy microServicePatientProxy;
    @Mock
    private MicroServiceHistoryProxy microServiceHistoryProxy;

    @BeforeEach
    void init() {
        assessmentServiceImpl = new AssessmentServiceImpl(microServicePatientProxy, microServiceHistoryProxy);
    }

    @Test
    void patientOlderThan30() {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Lastname", "Firstname", "M", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        boolean age = assessmentServiceImpl.patientOlderThan30(1);

        //THEN
        Assertions.assertTrue(age);
    }

    @Test
    void getGenderMale() {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Lastname", "Firstname", "M", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        String gender = assessmentServiceImpl.getGender(patientBean.getId());

        //THEN
        Assertions.assertEquals("MALE", gender);
    }

    @Test
    void getGenderFemale() {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Lastname", "Firstname", "F", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        String gender = assessmentServiceImpl.getGender(patientBean.getId());

        //THEN
        Assertions.assertEquals("FEMALE", gender);
    }

    @Test
    void getTriggerWords() throws IOException {
        List<String> triggerWords = assessmentServiceImpl.getTriggerWords();
        Assertions.assertEquals(11, triggerWords.size());
        Assertions.assertEquals("Hémoglobine A1C", triggerWords.get(0));
    }

    @Test
    void getStatusNone() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);


        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("NONE", riskLevel.name());
    }

    @Test
    void getStatusBorderline() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","F", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("BORDERLINE", riskLevel.name());
    }

    @Test
    void getStatusInDangerWithMaleMore30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur Anormal Cholestérol Vertige Anticorps");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("IN_DANGER", riskLevel.name());
    }

    @Test
    void getStatusBorderlineWithMaleMore30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(1980, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur Anormal Cholestérol Vertige Anticorps Poids");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("BORDERLINE", riskLevel.name());
    }

    @Test
    void getStatusNoneWithFemaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","F", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("NONE", riskLevel.name());
    }

    @Test
    void getStatusInDangerWithFemaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","F", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur Poids");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("IN_DANGER", riskLevel.name());
    }

    @Test
    void getStatusEarlyOnsetWithFemaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","F", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur Poids Vertige Rechute Réaction");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("EARLY_ONSET", riskLevel.name());
    }

    @Test
    void getStatusNoneWithMaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("NONE", riskLevel.name());
    }

    @Test
    void getStatusInDangerithMaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille, Poids Fumeur Poids");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("IN_DANGER", riskLevel.name());
    }

    @Test
    void getStatusEarlyOnsetWithMaleLessThan30Years() throws IOException {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        List<HistoryBean.Observation> observations = new ArrayList<>();
        HistoryBean historyBean = new HistoryBean(1, observations);
        HistoryBean.Observation observation = new HistoryBean.Observation();

        observation.setNote("Taille Hémoglobine A1C Fumeur Poids Vertige Rechute Réaction");
        observations.add(0, observation);

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(historyBean.getPatientId())).thenReturn(historyBean);

        RiskLevel riskLevel = assessmentServiceImpl.getStatus(patientBean.getId());

        //THEN
        Assertions.assertEquals("EARLY_ONSET", riskLevel.name());
    }

    @Test
    void ageOfPatient() {
        //GIVEN
        PatientBean patientBean = new PatientBean(1, "Bob", "bob","M", LocalDate.of(2000, Month.JANUARY, 1), "address", "phone");

        //WHEN
        when(microServicePatientProxy.getPatientById(patientBean.getId())).thenReturn(Optional.of(patientBean));
        Integer age = assessmentServiceImpl.ageOfPatient(patientBean.getId());

        //THEN
        Assertions.assertEquals(23, age);

    }

}