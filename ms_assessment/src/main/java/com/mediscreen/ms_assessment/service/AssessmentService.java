package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.model.RiskLevel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AssessmentService {
    public Boolean patientOlderThan30(Integer id);
    String getGender(Integer id);
    List<String> getTriggerWords() throws IOException;
    RiskLevel getStatus(Integer id) throws IOException;
}
