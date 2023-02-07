package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.model.Assessment;

import java.io.IOException;

/**
 * The interface Assessment service.
 */
public interface AssessmentService {
    /**
     * Get assessment by id
     * @param id patient id
     * @return assessment for patient id
     * @throws IOException
     */
    Assessment getAssessmentById(Integer id) throws IOException;

    /**
     * Get assessment by lastname
     * @param lastname patient lastname
     * @return assessment for patient lastname
     * @throws IOException
     */
    Assessment getAssessmentByLastname(String lastname) throws IOException;
}
