package com.mediScreen.ms_patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String lastname;
    @NotNull
    private String firstname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birth;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    private String phone;

}
