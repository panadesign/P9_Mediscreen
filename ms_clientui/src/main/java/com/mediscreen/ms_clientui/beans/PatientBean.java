package com.mediscreen.ms_clientui.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class PatientBean {
    private Integer id;
    private String family;
    private String given;
    private Date birth;
    private String gender;
    private String address;
    private String phone;

}
