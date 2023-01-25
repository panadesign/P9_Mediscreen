package com.mediscreen.ms_assessment.proxies;

import com.mediscreen.ms_assessment.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "microservice-patients", url = "${feign.ms-patient.url}")
public interface MicroServicePatientProxy {

    @GetMapping("/patients/{id}")
    Optional<PatientBean> getPatientById(@PathVariable("id") Integer id);

}
