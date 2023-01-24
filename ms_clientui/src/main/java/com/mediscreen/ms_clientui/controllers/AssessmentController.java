package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.AssessmentBean;
import com.mediscreen.ms_clientui.beans.HistoryBean;
import com.mediscreen.ms_clientui.beans.NoteBean;
import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServiceAssessmentProxy;
import com.mediscreen.ms_clientui.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Log4j2
@Controller
public class AssessmentController {

    private final MicroServiceAssessmentProxy microServiceAssessmentProxy;
    private final MicroServicePatientProxy microServicePatientProxy;

    public AssessmentController(MicroServiceAssessmentProxy microServiceAssessmentProxy, MicroServicePatientProxy microServicePatientProxy) {
        this.microServiceAssessmentProxy = microServiceAssessmentProxy;
        this.microServicePatientProxy = microServicePatientProxy;
    }

    @GetMapping("/patients/{id}/assessment")
    public String get(Model model, @PathVariable("id") Integer id) {
        log.debug("Access to patient's assessment");
        PatientBean patientBean = microServicePatientProxy.getPatientById(id);
        HistoryBean assessmentBean = microServiceAssessmentProxy.get(patientBean.getId());

        model.addAttribute("assessmentBean", assessmentBean);

        return "history/patientHistory";
    }


}