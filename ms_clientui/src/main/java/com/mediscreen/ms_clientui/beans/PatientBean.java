package com.mediscreen.ms_clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
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
