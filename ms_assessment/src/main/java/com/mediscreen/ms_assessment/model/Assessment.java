package com.mediscreen.ms_assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Assessment {
    private String riskLevel;
    private String lastname;
    private String firstname;
    private String gender;
    private Integer age;
}
