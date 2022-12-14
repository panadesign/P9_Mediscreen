package com.mediscreen.ms_history.proxies;

import com.mediscreen.ms_history.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface MicroServicePatientProxy {

    @GetMapping("/patients/{id}")
    PatientBean getPatientById(@PathVariable("id") Integer id);

}
