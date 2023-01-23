package com.example.spamclassifier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

    private Long id;
    private String subject;
    private String message;
    private LocalDateTime createdAt;
    private UserDTO sender;
    private UserDTO receiver;
    private Boolean spam;
}
