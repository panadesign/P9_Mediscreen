package com.mediscreen.ms_assessment.model;

import com.mediscreen.ms_assessment.beans.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Assessment {
    private String riskLevel;
    private String lastname;
    private String firstname;
    private GenderEnum gender;
    private Integer age;
}
