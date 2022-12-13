package com.mediScreen.ms_patient.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Date;

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
    private ZonedDateTime birth;
    @NotEmpty(message = "Gender cannot be empty")
    private String gender;
    @NotEmpty(message = "Address cannot be empty")
    private String address;
    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

}
