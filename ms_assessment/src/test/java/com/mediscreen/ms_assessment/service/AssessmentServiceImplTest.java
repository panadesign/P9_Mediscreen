package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.GenderEnum;
import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.model.Assessment;
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
    void getAssessmentById() throws IOException {
        //GIVEN
        List<HistoryBean.Observation> observations = new ArrayList<>();
        PatientBean patientBean = new PatientBean(1, "lastname", "firstname", GenderEnum.F, LocalDate.now(), "address", "phone");
        HistoryBean historyBean = new HistoryBean(1, observations);

        observations.add(new HistoryBean.Observation("Aucun mot clé", LocalDate.now()));

        //WHEN
        when(microServicePatientProxy.getPatientById(1)).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(1)).thenReturn(historyBean);

        Assessment assessment = assessmentServiceImpl.getAssessmentById(1);

        //THEN
        Assertions.assertEquals("NONE", assessment.getRiskLevel());
    }

    @Test
    void getAssessmentBorderlineForFemale() throws IOException {
        //GIVEN
        LocalDate birthdate = LocalDate.parse("1980-12-12");
        List<HistoryBean.Observation> observations = new ArrayList<>();
        PatientBean patientBean = new PatientBean(1, "lastname", "firstname", GenderEnum.F, birthdate, "address", "phone");
        HistoryBean historyBean = new HistoryBean(1, observations);

        observations.add(new HistoryBean.Observation("Poids Cholestérol Vertige", LocalDate.now()));

        //WHEN
        when(microServicePatientProxy.getPatientById(1)).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(1)).thenReturn(historyBean);

        Assessment assessment = assessmentServiceImpl.getAssessmentById(1);

        //THEN
        Assertions.assertEquals("BORDERLINE", assessment.getRiskLevel());
    }

    @Test
    void getAssessmentInDanger() throws IOException {
        //GIVEN
        LocalDate birthdate = LocalDate.parse("1980-12-12");
        List<HistoryBean.Observation> observations = new ArrayList<>();
        PatientBean patientBean = new PatientBean(1, "lastname", "firstname", GenderEnum.F, birthdate, "address", "phone");
        HistoryBean historyBean = new HistoryBean(1, observations);

        observations.add(new HistoryBean.Observation("Poids Cholestérol Vertige Rechute Réaction Fumeur", LocalDate.now()));

        //WHEN
        when(microServicePatientProxy.getPatientById(1)).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(1)).thenReturn(historyBean);

        Assessment assessment = assessmentServiceImpl.getAssessmentById(1);

        //THEN
        Assertions.assertEquals("IN_DANGER", assessment.getRiskLevel());
    }

    @Test
    void getAssessmentEarlyOnset() throws IOException {
        //GIVEN
        LocalDate birthdate = LocalDate.parse("1980-12-12");
        List<HistoryBean.Observation> observations = new ArrayList<>();
        PatientBean patientBean = new PatientBean(1, "lastname", "firstname", GenderEnum.M, birthdate, "address", "phone");
        HistoryBean historyBean = new HistoryBean(1, observations);

        observations.add(new HistoryBean.Observation("Poids Cholestérol Vertige Rechute Réaction Fumeur Anormal Anticorps", LocalDate.now()));

        //WHEN
        when(microServicePatientProxy.getPatientById(1)).thenReturn(Optional.of(patientBean));
        when(microServiceHistoryProxy.get(1)).thenReturn(historyBean);

        Assessment assessment = assessmentServiceImpl.getAssessmentById(1);

        //THEN
        Assertions.assertEquals("EARLY_ONSET", assessment.getRiskLevel());
    }

//    @Test
//    void getAssessmentByLastname() throws IOException {
//        List<HistoryBean.Observation> observations = new ArrayList<>();
//        PatientBean patientBean = new PatientBean(1, "lastname", "firstname", GenderEnum.F, LocalDate.now(), "address", "phone");
//        HistoryBean historyBean = new HistoryBean(1, observations);
//
//        observations.add(new HistoryBean.Observation("Aucun mot clé", LocalDate.now()));
//
//        when(microServicePatientProxy.getPatientById(1)).thenReturn(Optional.of(patientBean));
//        when(microServiceHistoryProxy.get(1)).thenReturn(historyBean);
//
//        Assessment assessment = assessmentServiceImpl.getAssessmentByLastname("lastname");
//
//        Assertions.assertEquals("NONE", assessment.getRiskLevel());
//    }
}