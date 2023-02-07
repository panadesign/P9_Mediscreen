package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * The interface Micro service patient proxy.
 */
@FeignClient(name = "microservice-patients", url = "${feign.ms-patient.url}")
public interface MicroServicePatientProxy {

    /**
     * Gets all patients.
     *
     * @return the all patients
     */
    @GetMapping(value = "/patients")
    List<PatientBean> getAllPatients();

    /**
     * Gets patient by id.
     *
     * @param id the id
     * @return the patient by id
     */
    @GetMapping("/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

    /**
     * Update patient patient bean.
     *
     * @param id          the id
     * @param patientBean the patient bean
     * @return the patient bean
     */
    @PostMapping ("/patients/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id, PatientBean patientBean);

    /**
     * Add patient.
     *
     * @param patientBean the patient bean
     */
    @PostMapping("/patients")
    void addPatient(PatientBean patientBean);
}