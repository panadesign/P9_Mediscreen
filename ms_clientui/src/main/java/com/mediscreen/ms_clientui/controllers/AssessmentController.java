package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.AssessmentBean;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServiceAssessmentProxy;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * The type Assessment controller.
 */
@Log4j2
@Controller
public class AssessmentController {

    private final MicroServiceAssessmentProxy microServiceAssessmentProxy;
    private final MicroServicePatientProxy microServicePatientProxy;

    /**
     * Instantiates a new Assessment controller.
     *
     * @param microServiceAssessmentProxy the micro service assessment proxy
     * @param microServicePatientProxy    the micro service patient proxy
     */
    public AssessmentController(MicroServiceAssessmentProxy microServiceAssessmentProxy, MicroServicePatientProxy microServicePatientProxy) {
        this.microServiceAssessmentProxy = microServiceAssessmentProxy;
        this.microServicePatientProxy = microServicePatientProxy;
    }

    /**
     * Get assessment of the patient.
     *
     * @param model the model
     * @param param    string
     * @return assessment of the patient by id or lastname
     */
    @GetMapping("/patients/{param}/assessment")
    public String get(Model model, @PathVariable String param) {
        log.debug("Access to patient's assessment");

        if(param.matches(("-?\\d+"))) {
            log.info("Get assessment by id: " + param);
            PatientBean patientBean = microServicePatientProxy.getPatientById(parseInt(param));
            AssessmentBean assessmentBean = microServiceAssessmentProxy.get(patientBean.getId());
            model.addAttribute("assessmentBean", assessmentBean);
            return "assessment/assessment";
        }

        log.info("Get assessment by lastname: " + param);
        PatientBean patientBean = microServicePatientProxy.getPatientByLastname((param));
        AssessmentBean assessmentBean = microServiceAssessmentProxy.get(patientBean.getId());
        model.addAttribute("assessmentBean", assessmentBean);
        return "assessment/assessment";
    }
}