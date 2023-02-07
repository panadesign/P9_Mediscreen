package com.mediscreen.ms_assessment.controllers;

import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.service.AssessmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * The type Assessment controller.
 */
@Log4j2
@RestController
public class AssessmentController {
    private final AssessmentService assessmentService;

    /**
     * Instantiates a new Assessment controller.
     *
     * @param assessmentService the assessment service
     */
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    /**
     * Gets assessment by param. Param can be id or lastname
     *
     * @param param the param
     * @return the assessment by param
     * @throws IOException the io exception
     */
    @GetMapping("patients/{param}/assessment")
    public Assessment getAssessmentByParam(@PathVariable String param) throws IOException {
        if(param.matches(("-?\\d+"))) {
            log.info("Get assessment by id: " + param);
            return assessmentService.getAssessmentById(parseInt(param));
        }
        log.info("Get assessment by lastname: " + param);
        return assessmentService.getAssessmentByLastname(param);
    }
}

