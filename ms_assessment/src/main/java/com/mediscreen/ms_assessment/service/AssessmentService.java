package com.mediscreen.ms_assessment.service;

import java.io.FileNotFoundException;
import java.util.List;

public interface AssessmentService {
    public Boolean patientOlderThan30(Integer id);
    String getGender(Integer id);
    List<String> getTriggerWords() throws FileNotFoundException;
    List<String> getNotes(Integer id);
}
