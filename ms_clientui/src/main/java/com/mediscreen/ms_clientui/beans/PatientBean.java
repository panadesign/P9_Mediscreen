package com.mediscreen.ms_clientui.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientBean {
    private Integer id;
    @NotBlank(message = "Ce champ est obligatoire")
    private String lastname;
    @NotBlank(message = "Ce champ est obligatoire")
    private String firstname;
    @NotBlank(message = "Gender cannot be empty")
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birth;
    @NotBlank(message = "Ce champ est obligatoire")
    private String address;
    @NotBlank(message = "Ce champ est obligatoire")
    private String phone;

}
