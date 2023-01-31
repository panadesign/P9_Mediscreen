package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "${feign.ms-patient.url}")
public interface MicroServicePatientProxy {

    @GetMapping(value = "/patients")
    List<PatientBean> getAllPatients();

    @GetMapping("/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

    @PostMapping ("/patients/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id, PatientBean patientBean);

    @PostMapping("/patients")
    void addPatient(PatientBean patientBean);
}