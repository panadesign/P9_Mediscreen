package com.mediscreen.ms_clientui.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
    private Integer id;
    @NonNull
    private String lastname;
    @NonNull
    private String firstname;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @NonNull
    private String gender;
    @NonNull
    private String address;
    @NonNull
    private String phone;

}
