package com.mediscreen.ms_history.proxies;

import com.mediscreen.ms_history.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface MicroServicePatientProxy {

    @GetMapping("/patients/{id}")
    Optional<PatientBean> getPatientById(@PathVariable("id") Integer id); // Todo <- Optional

}
