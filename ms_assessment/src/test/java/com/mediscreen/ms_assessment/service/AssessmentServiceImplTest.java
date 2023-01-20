package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
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
    void getTriggerWords() throws FileNotFoundException {
        List<String> triggerWords = assessmentServiceImpl.getTriggerWords();
        Assertions.assertEquals(11, triggerWords.size());
        Assertions.assertTrue(triggerWords.get(0).equals("Hémoglobine A1C"));
    }
}