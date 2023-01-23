package com.example.spamclassifier.exception;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private int status;

    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }
}
