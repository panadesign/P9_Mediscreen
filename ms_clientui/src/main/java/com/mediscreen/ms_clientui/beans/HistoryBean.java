package com.mediscreen.ms_clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
