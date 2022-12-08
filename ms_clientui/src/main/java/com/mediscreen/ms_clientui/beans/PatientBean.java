package com.mediscreen.ms_clientui.beans;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @NotBlank(message = "Gender cannot be empty")
    private String gender;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;

}
