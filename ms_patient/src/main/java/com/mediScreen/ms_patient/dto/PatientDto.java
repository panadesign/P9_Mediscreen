package com.mediScreen.ms_patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediScreen.ms_patient.model.Patient;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PatientDto {

    @Getter
    private Integer id;

    @NotEmpty(message = "Lastname cannot be empty")
    private String lastname;

    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    public PatientDto(Patient patient) {
        this.id = patient.getId();
        this.lastname = patient.getLastname();
        this.firstname = patient.getFirstname();
        this.birth = patient.getBirth();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.phone = patient.getPhone();
    }

}
