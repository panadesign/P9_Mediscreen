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
    void getAssessmentById() {
    }

    @Test
    void getAssessmentByLastname() {
    }
}