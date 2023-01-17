package com.mediscreen.ms_history.repository;

import com.mediscreen.ms_history.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {
}
