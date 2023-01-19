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

import java.time.LocalDate;
import java.time.Month;
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
}