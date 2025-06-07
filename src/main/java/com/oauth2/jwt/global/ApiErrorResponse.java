package com.oauth2.jwt.global;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiErrorResponse {

    private final List<FieldErrorResponse> fieldErrors;
    private final List<GlobalErrorResponse> globalErrors;


    public ApiErrorResponse(List<FieldErrorResponse> fieldErrors, List<GlobalErrorResponse> globalErrors) {
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }
}
