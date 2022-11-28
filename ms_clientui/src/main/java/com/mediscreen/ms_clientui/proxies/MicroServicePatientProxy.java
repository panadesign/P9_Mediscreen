package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface MicroServicePatientProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> allPatients();

    @GetMapping(value = "/patients/{id}")
    Optional<PatientBean> patientById(@PathVariable("id") Integer id);
}
