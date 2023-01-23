package com.example.spamclassifier.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BodyResponse {

    private int status;
    private String message;
    private Object data;

    public BodyResponse(Object data) {
        this.data = data;
    }

    public BodyResponse message(String message) {
        this.message = message;
        return this;
    }

    public BodyResponse status(int status) {
        this.status = status;
        return this;
    }
}
