package com.mediscreen.ms_clientui.beans;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {
    private Integer id;
    private String lastname;
    private String firstname;
    private Date birth;
    private String gender;
    private String address;
    private String phone;

}
