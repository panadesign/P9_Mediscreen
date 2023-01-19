package com.mediscreen.ms_assessment.service;

import com.mediscreen.ms_assessment.beans.HistoryBean;
import com.mediscreen.ms_assessment.beans.PatientBean;
import com.mediscreen.ms_assessment.proxies.MicroServiceHistoryProxy;
import com.mediscreen.ms_assessment.proxies.MicroServicePatientProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

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
        return numberOfYears > 30;
    }

//    public HistoryBean notes(Integer id) {
//
//        HistoryBean historyBean = microServiceHistoryProxy.get(id);
//        historyBean.getObservations();
//
//
//    }

    public Integer numberTriggerWords(Integer id) {
        return null;
    }

}
