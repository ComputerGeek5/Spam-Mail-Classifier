package com.example.spamclassifier.api.request;

import com.example.spamclassifier.enumerator.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;

    @JsonFormat(pattern = "yyyy-M-d")
    private LocalDate birthday;

    private String occupation;
    private String location;
}
