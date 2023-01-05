package com.mediScreen.ms_patient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.ZonedDateTimeType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public Patient(Integer id, String lastname, String firstname, ZonedDateTime birth, String gender, String address, String phone) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birth = birth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public Patient(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
