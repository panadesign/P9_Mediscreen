package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.dto.PatientDto;
import com.mediScreen.ms_patient.exceptions.ResourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * The type Patient controller.
 */
@Log4j2
@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private final PatientService patientService;

    /**
     * Instantiates a new Patient controller.
     *
     * @param patientService the patient service
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Gets all patients.
     *
     * @return the all patients
     */
    @GetMapping()
    public List<PatientDto> getAllPatients() {
        log.info("Get all patients");
        return patientService.getPatients().stream()
                .map(PatientDto::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets patient by param.
     *
     * @param param the param
     * @return the patient by id or lastname
     */
    @GetMapping("/{param}")
    public PatientDto getPatientByParam(@PathVariable String param) {
        if (param.matches(("-?\\d+"))) {
            log.info("Get patient with id " + param);
            Patient patient = patientService.findById(parseInt(param))
                    .orElseThrow(() -> new ResourceNotExistingException("Patient with id " + param + " doesn't exist"));

            return new PatientDto(patient);

        }
        log.info("Get patient with lastname " + param);
        Patient patient =  patientService.findByLastname(param)
                .orElseThrow(() -> new ResourceNotExistingException("Patient with lastname " + param + " doesn't exist"));

        return new PatientDto(patient);
    }

    /**
     * Update patient dto.
     *
     * @param id      the id
     * @param patient the patient
     * @return updated patient dto
     */
    @PostMapping("/{id}")
    public PatientDto updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        log.info("Patient : " + patient.getLastname() + " " + patient.getFirstname() + " is updated.");
        Patient patientToUpdate = patientService.update(id, patient);

        return new PatientDto(patientToUpdate);
    }

    /**
     * Add patient dto.
     *
     * @param patient the patient
     * @return a new patient dto
     */
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED, reason = "OK")
    public PatientDto addPatient(@RequestBody @Valid Patient patient) {
        log.info("Add a new patient");
        Patient patientToAdd =  patientService.add(patient);

        return new PatientDto(patientToAdd);
    }
}
