package com.mediScreen.ms_patient.controllers;

import com.mediScreen.ms_patient.exceptions.ResourceNotExistingException;
import com.mediScreen.ms_patient.model.Patient;
import com.mediScreen.ms_patient.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value = "/patients")
@Log4j2
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<Patient> getAllPatients() {
        log.debug("Get all patients");
        return patientService.getPatients();
    }

    @GetMapping("/{param}")
    public Patient getPatientByParam(@PathVariable String param) {
        if(param.matches(("-?\\d+"))) {
            return patientService.findById(parseInt(param)).orElseThrow(() -> new  ResourceNotExistingException("test"));
        }
        return patientService.findByLastname(param);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable("id")  Integer id, Patient patient) {
        log.debug("Patient : " + patient.getLastname() + " " + patient.getFirstname() + " is updated.");
        return patientService.update(id, patient);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED, reason = "OK")
    public Patient addPatient(@Valid Patient patient) {
        return patientService.add(patient);
    }
}
