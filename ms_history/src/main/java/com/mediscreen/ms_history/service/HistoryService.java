package com.mediscreen.ms_history.service;


import com.mediscreen.ms_history.domain.History;

import java.security.SecureRandom;

public interface HistoryService {
    History getHistoryById(Integer id);
    History addObservation(Integer id, String note);
}
