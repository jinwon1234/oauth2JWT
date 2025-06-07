package com.oauth2.jwt.global;

import lombok.Getter;

@Getter
public class FieldErrorResponse {

    private final String field;

    private final String message;

    public FieldErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
