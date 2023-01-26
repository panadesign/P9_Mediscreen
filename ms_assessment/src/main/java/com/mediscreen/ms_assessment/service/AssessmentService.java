package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.model.RiskLevel;

import java.io.IOException;
import java.util.List;

public interface AssessmentService {
    Assessment getAssessmentById(Integer id) throws IOException;
    Assessment getAssessmentByLastname(String lastname) throws IOException;
}
