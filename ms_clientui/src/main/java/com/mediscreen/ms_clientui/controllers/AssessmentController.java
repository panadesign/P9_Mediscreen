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
     * @param id    the id
     * @return assessment of the patient
     */
    @GetMapping("/patients/{id}/assessment")
    public String get(Model model, @PathVariable("id") Integer id) {
        log.debug("Access to patient's assessment");
        PatientBean patientBean = microServicePatientProxy.getPatientById(id);
        AssessmentBean assessmentBean = microServiceAssessmentProxy.get(patientBean.getId());

        model.addAttribute("assessmentBean", assessmentBean);

        return "assessment/assessment";
    }


}