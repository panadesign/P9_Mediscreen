package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.model.RiskLevel;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AssessmentServiceImpl implements AssessmentService {
    private final MicroServicePatientProxy microServicePatientProxy;
    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    public AssessmentServiceImpl(MicroServicePatientProxy microServicePatientProxy, MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    public Assessment getAssessmentById(Integer id) throws IOException {
        return generateAssessment(id);
    }

    public Assessment getAssessmentByLastname(String lastname) throws IOException {
        Optional<PatientBean> patientBean = microServicePatientProxy.getPatientByLastname(lastname);
        Integer patientsId = patientBean.get().getId();
        return generateAssessment(patientsId);
    }

    public Assessment generateAssessment(Integer id) throws IOException {
        Optional<PatientBean> patientBean = microServicePatientProxy.getPatientById(id);
        Integer age = ageOfPatient(id);
        RiskLevel status = getStatus(id);
        String gender = getGender(id);
        log.debug("Create an assessment for patient with id:" + id);
        return new Assessment(status.name(), patientBean.get().getLastname(), patientBean.get().getFirstname(), gender, age);
    }

    public Integer ageOfPatient(Integer id) {
        Optional<PatientBean> patientBean = microServicePatientProxy.getPatientById(id);
        LocalDate birthdate = patientBean.get().getBirth();

        Period age = Period.between(birthdate, LocalDate.now());
        return age.getYears();
    }

    public Boolean patientOlderThan30(Integer id) {
        Integer numberOfYears = ageOfPatient(id);
        log.debug("Verify if patient with id " + id + "is older than 30 years");
        return numberOfYears > 30;
    }

    public String getGender(Integer id) {
        Optional<PatientBean> patientBean = microServicePatientProxy.getPatientById(id);
        String gender = patientBean.get().getGender();
        log.debug("Verify if patient with id " + id + "is male or female");

        if (Objects.equals(gender, "M")) {
            return "MALE";
        } else return "FEMALE";
    }


    public List<String> getTriggerWords() throws IOException {

        InputStream is = getClass().getResourceAsStream("/triggerWords.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        ArrayList<String> triggerWordsList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            while (line != null) {
                triggerWordsList.add(line);
                line = br.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }


        return triggerWordsList;
    }

    public RiskLevel getStatus(Integer id) throws IOException {
        HistoryBean historyBean = microServiceHistoryProxy.get(id);

        int countTriggerWord = 0;

        List<String> observations =
                historyBean.getObservations()
                        .stream()
                        .map(HistoryBean.Observation::getNote)
                        .collect(Collectors.toList());

        for (String observation : observations) {
            for (String triggerWord : getTriggerWords()) {
                if (observation.contains(triggerWord)) {
                    countTriggerWord++;
                }
            }
        }

        if (countTriggerWord < 2 && patientOlderThan30(id)) {
            return RiskLevel.NONE;
        } else if (patientOlderThan30(id) && countTriggerWord >= 2 && countTriggerWord < 6) {
            return RiskLevel.BORDERLINE;
        } else if (patientOlderThan30(id) && countTriggerWord >= 6 && countTriggerWord < 8) {
            return RiskLevel.IN_DANGER;
        } else if (patientOlderThan30(id) && countTriggerWord >= 8) {
            return RiskLevel.BORDERLINE;
        } else if (!patientOlderThan30(id) && countTriggerWord < 4 && (Objects.equals(getGender(id), "FEMALE"))) {
            return RiskLevel.NONE;
        } else if (!patientOlderThan30(id) && countTriggerWord >= 4 && countTriggerWord < 7 && (Objects.equals(getGender(id), "FEMALE"))) {
            return RiskLevel.IN_DANGER;
        } else if (!patientOlderThan30(id) && countTriggerWord >= 7 && (Objects.equals(getGender(id), "FEMALE"))) {
            return RiskLevel.EARLY_ONSET;
        } else if (!patientOlderThan30(id) && countTriggerWord < 3 && (Objects.equals(getGender(id), "MALE"))) {
            return RiskLevel.NONE;
        } else if (!patientOlderThan30(id) && countTriggerWord >= 3 && countTriggerWord < 5 && (Objects.equals(getGender(id), "MALE"))) {
            return RiskLevel.IN_DANGER;
        } else if (!patientOlderThan30(id) && countTriggerWord >= 5 && (Objects.equals(getGender(id), "MALE"))) {
            return RiskLevel.EARLY_ONSET;
        }
        return null;
    }


}
