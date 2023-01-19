package com.mediscreen.ms_clientui.beans;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryBean {
    private Integer patientId;
    private List<Observation> observations;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Observation {
        @NotNull
        private String note;
        private LocalDate dateTime;
    }
}
