package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.NoteBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class AssessmentServiceImpl implements AssessmentService {
    private final MicroServicePatientProxy microServicePatientProxy;
    private final MicroServiceHistoryProxy microServiceHistoryProxy;

    public AssessmentServiceImpl(MicroServicePatientProxy microServicePatientProxy, MicroServiceHistoryProxy microServiceHistoryProxy) {
        this.microServicePatientProxy = microServicePatientProxy;
        this.microServiceHistoryProxy = microServiceHistoryProxy;
    }

    public Boolean patientOlderThan30(Integer id) {
        Optional<PatientBean> patientBean = microServicePatientProxy.getPatientById(id);
        LocalDate birthdate = patientBean.get().getBirth();

        Period age = Period.between(birthdate, LocalDate.now());
        int numberOfYears = age.getYears();
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


    public List<String> getTriggerWords() throws FileNotFoundException {
        File triggerFile = new File("src/main/resources/triggerWords.txt");

        BufferedReader filepath = new BufferedReader(new FileReader(triggerFile));

        ArrayList<String> triggerWordsList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(filepath);
            String line = reader.readLine();

            while (line != null) {
                triggerWordsList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }


        return triggerWordsList;
    }



    public List<String> getNotes(Integer id) {
        HistoryBean historyBean = microServiceHistoryProxy.get(id);
        historyBean.getObservations()
                .stream().collect(Collectors.toList());

    //        return historyBean.getObservations()
//                .stream()
//                .map(n -> n.note())
//                .collect(Collectors.toList());

    }

    public Integer getNumberOfTrigger() throws FileNotFoundException {
//        boucler mots dans les notes
        for (String triggerWord : getTriggerWords()) {

        }
        return null;
    }


//    public String level(Integer id) throws FileNotFoundException {
//
//        String gender = getGender(id);
//        Boolean age = patientOlderThan30(id);
//         totalTriggerWords = getTriggerWords();
//        if(gender.equals("MALE") && age && nombre de declencheurs) {
//            return "none";
//        }
//    }

}
