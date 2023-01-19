package com.mediscreen.ms_assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TriggerWords {
    public static List<String> triggerWords = List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");
}