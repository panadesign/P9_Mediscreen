package com.mediScreen.ms_patient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
