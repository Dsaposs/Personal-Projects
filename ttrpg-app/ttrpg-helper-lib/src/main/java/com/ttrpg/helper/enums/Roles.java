package com.ttrpg.helper.enums;

import lombok.Getter;

@Getter
public enum Roles {
    USER("USER"),
    TEMP_USER("TEMP_USER"),
    ADMIN("ADMIN"),
    SERVICE("SERVICE");

    private final String role;

    Roles(String value) {
        this.role = value;
    }
}