package com.mediscreen.ms_clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
    private Integer id;
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastname;
    @NotEmpty(message = "Lastname cannot be empty")
    private String firstname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Lastname cannot be empty")
    private Date birth;
    @NotEmpty(message = "Lastname cannot be empty")
    private String gender;
    @NotEmpty(message = "Lastname cannot be empty")
    private String address;
    @NotEmpty(message = "Lastname cannot be empty")
    private String phone;

}
