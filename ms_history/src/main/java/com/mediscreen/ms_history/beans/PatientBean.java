package com.mediscreen.ms_history.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientBean {
    private Integer id;
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;
    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "Gender cannot be empty")
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date birth;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;

}

