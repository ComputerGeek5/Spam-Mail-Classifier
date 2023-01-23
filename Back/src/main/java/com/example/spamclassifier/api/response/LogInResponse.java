package com.example.spamclassifier.api.response;

import com.example.spamclassifier.api.response.resource.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogInResponse {

    private UserResponse user;
    private String token;
}
