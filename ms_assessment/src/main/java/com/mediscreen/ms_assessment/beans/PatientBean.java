package com.mediscreen.ms_assessment.beans;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private GenderEnum gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birth;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;

    public Integer getAge(){
        return LocalDate.now().getYear() - birth.getYear();
    }

    public boolean isOlderThan30(){
        return getAge() > 30;
    }

}

