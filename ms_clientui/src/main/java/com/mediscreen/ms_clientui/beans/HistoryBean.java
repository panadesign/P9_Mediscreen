package com.mediscreen.ms_clientui.beans;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryBean {
    private Integer patientId;
    private List<Observation> observations;

    public HistoryBean addObservation(String note) {
        observations.add(new Observation(note));
        return this;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    static class Observation {
        private String note;
        private LocalDateTime dateTime;

        public Observation(String note) {
            this.note = note;
            dateTime = LocalDateTime.now();
        }
    }
}
