package com.mediscreen.ms_history.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type History.
 */
@Data
@Document("history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class History {

    @Id
    private Integer patientId;
    private List<Observation> observations;

    /**
     * Instantiates a new History.
     *
     * @param id the history id
     */
    public History(Integer id) {
        this(id, new ArrayList<>());
    }

    /**
     * Add observation history.
     *
     * @param note the note
     * @return the history
     */
    public History addObservation(String note) {
        observations.add(new Observation(observations.size()+1, note));
        return this;
    }

    public History updateObservation(Long noteId, String note) {
        observations
                .stream()
                .filter(o -> o.getNoteId().equals(noteId))
                .findFirst()
                .ifPresent(observation -> observation.setNote(note));
        return this;
    }

    /**
     * The type Observation.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Observation {
        @Id
        private Long noteId;

        @NotEmpty(message = "Note cannot be empty")
        private String note;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateTime = LocalDate.now();

        /**
         * Instantiates a new Observation.
         *
         * @param note the note
         */
        public Observation(Integer id, String note) {
            this.noteId = Long.valueOf(id);
            this.note = note;
        }


    }
}
