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

    @GetMapping(value = "/patients/{lastname}")
    List<PatientBean> patientsByLastname(@PathVariable("lastname") String lastname);

    @GetMapping("/patients/update/{id}")
    Optional<PatientBean> updateFormPatient(@PathVariable("id") Integer id);
}
