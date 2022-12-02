package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface MicroServicePatientProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> allPatients();

    @GetMapping("/patients")
    PatientBean patient(@RequestParam Integer id);

    @GetMapping("/patients/edit")
    Optional<PatientBean> formUpdatePatient(@RequestParam Integer id);

}
