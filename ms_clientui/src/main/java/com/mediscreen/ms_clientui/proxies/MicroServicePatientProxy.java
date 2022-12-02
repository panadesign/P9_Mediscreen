package com.mediscreen.ms_clientui.proxies;

import com.mediscreen.ms_clientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/patients/edit")
    void updatePatient(@RequestParam Integer id, @RequestBody PatientBean patientBean);

}
