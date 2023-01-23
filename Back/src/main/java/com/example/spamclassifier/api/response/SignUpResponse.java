package com.example.spamclassifier.api.response;

import com.example.spamclassifier.api.response.resource.UserResponse;
import com.example.spamclassifier.dto.UserDTO;
import com.example.spamclassifier.enumerator.Gender;
import com.example.spamclassifier.enumerator.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpResponse {

    private UserResponse user;
}
