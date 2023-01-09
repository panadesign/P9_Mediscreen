package com.mediscreen.ms_history.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("history")
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    private Integer patientId;
    private List<Observation> observations;

    public History(Integer id) {
        this(id, new ArrayList<>());
    }

    public History addObservation(String note) {
        observations.add(new Observation(note));
        return this;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Observation {
        private String note;
        private LocalDateTime dateTime = LocalDateTime.now() ;

        public Observation(String note) {
            this.note = note;
        }
    }
}
