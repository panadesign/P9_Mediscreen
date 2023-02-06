package com.mediscreen.ms_clientui.controllers;

import com.mediscreen.ms_clientui.beans.PatientBean;
import com.mediscreen.ms_clientui.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Patient controller.
 */
@Log4j2
@Controller
@RequestMapping(value = "/patients")
public class PatientController {

    private final MicroServicePatientProxy microServicePatientProxy;

    /**
     * Instantiates a new Patient controller.
     *
     * @param microServicePatientProxy the micro service patient proxy
     */
    public PatientController(MicroServicePatientProxy microServicePatientProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
    }

    /**
     * Gets all patients.
     *
     * @param model the model
     * @return all patients
     */
    @GetMapping()
    public String getAllPatients(Model model) {
        List<PatientBean> patients = microServicePatientProxy.getAllPatients();
        model.addAttribute("patients", patients);
        log.info("Get all patients");
        return "patient/patients";
    }

    /**
     * Gets patient by id.
     *
     * @param model the model
     * @param id    the id
     * @return patient by id
     */
    @GetMapping("/{id}")
    public String getPatientById(Model model, @PathVariable("id") Integer id) {

        log.info("Access to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);

        return "patient/patient";
    }

    /**
     * Form update patient string.
     *
     * @param model the model
     * @param id    the id
     * @return the update form
     */
    @GetMapping("/{id}/edit")
    public String formUpdatePatient(Model model, @PathVariable("id") Integer id) {

        log.info("Access update form to patient with id = " + id);

        PatientBean patient = microServicePatientProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/patientUpdate";
    }

    /**
     * Update patient.
     *
     * @param model       the model
     * @param id          the id
     * @param patientBean the patient bean
     * @param result      the result
     * @return list of patients after update
     */
    @PostMapping("/{id}/edit")
    public String updatePatient(Model model, @PathVariable("id") Integer id, @Valid PatientBean patientBean, BindingResult result) {

        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "redirect:/patients/{id}/edit";
        }

        microServicePatientProxy.updatePatient(id, patientBean);
        model.addAttribute("patient", microServicePatientProxy.getAllPatients());

        log.info("Patient : " + patientBean.getLastname() + " " + patientBean.getFirstname() + " is updated.");
        return "redirect:/patients";
    }

    /**
     * Form add patient.
     *
     * @param model       the model
     * @param patientBean the patient bean
     * @return the add patient form
     */
    @GetMapping("/add")
    public String formAddPatient(Model model, PatientBean patientBean) {
        log.info("Access to patient add form");
        return "patient/patientAdd";
    }

    /**
     * Add a new patient.
     *
     * @param model       the model
     * @param patientBean the patient bean
     * @param result      the result
     * @return the list of patient after add
     */
    @PostMapping()
    public String add(Model model, @Valid PatientBean patientBean, BindingResult result) {
        log.info("Add a new patient");
        if(result.hasErrors()) {
            log.error("Error: " + result.getFieldError());
            return "patient/patientAdd";
        }

        microServicePatientProxy.addPatient(patientBean);
        model.addAttribute("patients", microServicePatientProxy.getAllPatients());

        log.info("Patient : " + patientBean.getLastname() + " " +  patientBean.getFirstname() + " is created.");
        return "redirect:/patients";
    }
}
