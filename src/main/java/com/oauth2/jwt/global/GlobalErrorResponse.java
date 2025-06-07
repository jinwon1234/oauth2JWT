package com.oauth2.jwt.global;

import lombok.Getter;

@Getter
public class GlobalErrorResponse {

    private final String message;

    public GlobalErrorResponse(String message) {
        this.message = message;
    }
}
