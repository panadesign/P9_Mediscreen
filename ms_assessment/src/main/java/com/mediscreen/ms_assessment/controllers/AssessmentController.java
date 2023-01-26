package com.mediscreen.ms_assessment.controllers;

import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.service.AssessmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@Log4j2
@RestController
public class AssessmentController {
    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("patients/{param}/assessment")
    public Assessment getPatientByParam(@PathVariable String param) throws IOException {
        if(param.matches(("-?\\d+"))) {
            return assessmentService.getAssessmentById(parseInt(param));
        }
        return assessmentService.getAssessmentByLastname(param);
    }
}

