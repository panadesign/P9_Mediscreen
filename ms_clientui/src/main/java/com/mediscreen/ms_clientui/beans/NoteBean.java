package com.mediscreen.ms_clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteBean {

    @Id
    private Long noteId;
    @NotBlank(message = "Note cannot be empty")
    private String note;
}
