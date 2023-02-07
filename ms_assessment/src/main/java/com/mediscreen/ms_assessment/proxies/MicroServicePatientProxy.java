package com.mediscreen.ms_assessment.proxies;

import com.mediscreen.ms_assessment.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * The interface Micro service patient proxy.
 */
@FeignClient(name = "microservice-patients", url = "${feign.ms-patient.url}")
public interface MicroServicePatientProxy {

    /**
     * Gets patient by id.
     *
     * @param id the id
     * @return the patient by id
     */
    @GetMapping("/patients/{id}")
    Optional<PatientBean> getPatientById(@PathVariable("id") Integer id);

    /**
     * Gets patient by lastname.
     *
     * @param lastname the lastname
     * @return the patient by lastname
     */
    @GetMapping("/patients/{lastname}")
    Optional<PatientBean> getPatientByLastname(@PathVariable("lastname") String lastname);
}
