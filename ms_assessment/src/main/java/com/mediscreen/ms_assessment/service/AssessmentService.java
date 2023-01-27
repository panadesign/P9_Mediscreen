package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.model.Assessment;

import java.io.IOException;

public interface AssessmentService {
    Assessment getAssessmentById(Integer id) throws IOException;
    Assessment getAssessmentByLastname(String lastname) throws IOException;
}
