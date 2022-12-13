package com.mediscreen.ms_history.service;


import com.mediscreen.ms_history.domain.History;

public interface HistoryService {
    History getHistoryById(String id);
}
