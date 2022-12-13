package com.mediscreen.ms_history.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    @Id
    private String patId;
    private String Patient;
    private String observations;

}
