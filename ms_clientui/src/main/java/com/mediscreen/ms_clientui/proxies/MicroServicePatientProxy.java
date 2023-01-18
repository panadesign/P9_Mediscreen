package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@FeignClient(name = "microservice-patients", url = "${feign.ms-patient.url}")
public interface MicroServicePatientProxy {

    @GetMapping(value = "/patients")
    List<PatientBean> getAllPatients();

    @GetMapping("/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

    @PutMapping("/patients/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id, @RequestBody PatientBean patientBean);

    @PostMapping("/patients")
    void addPatient(PatientBean patientBean);

}