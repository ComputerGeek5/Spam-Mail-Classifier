package com.example.spamclassifier.enumerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role {
    ADMIN("Admin", "admin"),
    USER("User", "user");

    private String name;
    private String Description;
}
