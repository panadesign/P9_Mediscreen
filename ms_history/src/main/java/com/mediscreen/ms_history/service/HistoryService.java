package com.mediscreen.ms_history.service;


import com.mediscreen.ms_history.model.History;

public interface HistoryService {
    History getHistoryById(Integer id);
    History addObservation(Integer id, String note);
}
