package com.example.spamclassifier.dto;

import com.example.spamclassifier.enumerator.Gender;
import com.example.spamclassifier.enumerator.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;
    private String occupation;
    private String location;
    private Role role;
}
