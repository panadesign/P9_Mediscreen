package com.mediscreen.ms_clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentBean {
    private String riskLevel;
    private String lastname;
    private String firstname;
    private String gender;
    private Integer age;
}
