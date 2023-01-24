package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.model.RiskLevel;

import java.io.IOException;
import java.util.List;

public interface AssessmentService {
    Assessment generateAssessment(Integer id) throws IOException;
    Integer ageOfPatient(Integer id);
    public Boolean patientOlderThan30(Integer id);
    String getGender(Integer id);
    List<String> getTriggerWords() throws IOException;
    RiskLevel getStatus(Integer id) throws IOException;
}
