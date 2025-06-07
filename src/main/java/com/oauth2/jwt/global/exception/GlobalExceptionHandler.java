package com.oauth2.jwt.global.exception;

import com.oauth2.jwt.global.ApiErrorResponse;
import com.oauth2.jwt.global.FieldErrorResponse;
import com.oauth2.jwt.global.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(error.getField(), error.getDefaultMessage()))
                .toList();

        List<GlobalErrorResponse> globals = ex.getBindingResult().getGlobalErrors().
                stream()
                .map(error -> new GlobalErrorResponse(error.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse(fields, globals));
    }
}
