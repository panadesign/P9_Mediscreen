package com.mediscreen.ms_assessment.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteBean {

    @NotBlank(message = "Note cannot be empty")
    String note;
}
