package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.GenderEnum;
import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.model.Assessment;
import com.mediscreen.ms_assessment.model.RiskLevel;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Assessment service.
 */
@Service
@Log4j2
public class AssessmentServiceImpl implements AssessmentService {
    private final MicroServicePatientProxy microServicePatientProxy;
    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    /**
     * Instantiates a new Assessment service.
     *
     * @param microServicePatientProxy the micro service patient proxy
     * @param microServiceHistoryProxy the micro service history proxy
     */
    public AssessmentServiceImpl(MicroServicePatientProxy microServicePatientProxy, MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    /**
     * Get assessment by id
     * @param id patient id
     * @return assessment for patient id
     * @throws IOException
     */
    public Assessment getAssessmentById(Integer id) throws IOException {
        log.info("Get assessment by id with id: " + id);
        return generateAssessment(id);
    }

    /**
     * Get assessment by lastname
     * @param lastname patient lastname
     * @return assessment for patient lastname
     * @throws IOException
     */
    public Assessment getAssessmentByLastname(String lastname) throws IOException {
        PatientBean patientBean = microServicePatientProxy.getPatientByLastname(lastname)
                .orElseThrow();
        Integer patientId = patientBean.getId();
        log.info("Get assessment by lastname with lastname " + lastname);
        return generateAssessment(patientId);
    }


    private Assessment generateAssessment(Integer id) throws IOException {
        PatientBean patientBean = microServicePatientProxy.getPatientById(id)
                .orElseThrow();
        HistoryBean historyBean = microServiceHistoryProxy.get(id);

        RiskLevel status = getStatus(patientBean, historyBean);
        log.info("Create an assessment for patient with id:" + id);
        assert status != null;
        return new Assessment(status.name(), patientBean.getLastname(), patientBean.getFirstname(), patientBean.getGender(), patientBean.getAge());
    }


    private List<String> getTriggerWords() throws IOException {

        InputStream is = getClass().getResourceAsStream("/triggerWords.txt");
        assert is != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        BufferedReader br = new BufferedReader(reader);

        List<String> triggerWordsList = br.lines().collect(Collectors.toList());

        reader.close();
        log.info("Get trigger words in list");
        return triggerWordsList;
    }

    private RiskLevel getStatus(PatientBean patient, HistoryBean history) throws IOException {
        int countTriggerWord;

        List<String> observations =
                history.getObservations()
                        .stream()
                        .map(HistoryBean.Observation::getNote)
                        .collect(Collectors.toList());


        List<String> words = getTriggerWords();
        countTriggerWord = observations.stream().mapToInt(observation -> (int) words.stream().filter(observation::contains).count()).sum();

        log.info("Get status");

        if (isNone(patient, countTriggerWord)) {
            return RiskLevel.NONE;
        }

        if (isBorderline(patient, countTriggerWord)) {
            return RiskLevel.BORDERLINE;
        }

        if (isInDanger(patient, countTriggerWord)) {
            return RiskLevel.IN_DANGER;
        }

        if (isEarlyOnset(patient, countTriggerWord)) {
            return RiskLevel.EARLY_ONSET;
        }

        return null;
    }

    private static boolean isNone(PatientBean patient, Integer countTriggerWord) {
        return (countTriggerWord < 2 && patient.isOlderThan30())
                || (!patient.isOlderThan30() && countTriggerWord < 4 && patient.getGender().equals(GenderEnum.F))
                || (!patient.isOlderThan30() && countTriggerWord < 3 && patient.getGender().equals(GenderEnum.M));

    }

    private boolean isBorderline(PatientBean patient, Integer countTriggerWord) {
        return (patient.isOlderThan30() && countTriggerWord >= 2 && countTriggerWord < 6);

    }

    private static boolean isInDanger(PatientBean patient, Integer countTriggerWord) {
        return (patient.isOlderThan30() && countTriggerWord >= 6 && countTriggerWord < 8)
                || (!patient.isOlderThan30() && countTriggerWord >= 4 && countTriggerWord < 7 && patient.getGender().equals(GenderEnum.F))
                || (!patient.isOlderThan30() && countTriggerWord >= 3 && countTriggerWord < 5 && patient.getGender().equals(GenderEnum.M));
    }

    private static boolean isEarlyOnset(PatientBean patient, Integer countTriggerWord) {
        return (!patient.isOlderThan30() && countTriggerWord >= 7 && patient.getGender().equals(GenderEnum.F))
                || (!patient.isOlderThan30() && countTriggerWord >= 5 && patient.getGender().equals(GenderEnum.M))
                || (patient.isOlderThan30() && countTriggerWord >= 8);
    }
}
