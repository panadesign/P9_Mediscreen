package com.mediscreen.ms_history.repository;

import com.mediscreen.ms_history.domain.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends MongoRepository<History, Integer> {
}
