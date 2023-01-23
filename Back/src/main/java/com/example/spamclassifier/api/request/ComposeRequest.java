package com.example.spamclassifier.api.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposeRequest {

    private String subject;
    private String message;
    private UserResource receiver;
    private UserResource sender;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResource {

        private Long id;
    }
}
