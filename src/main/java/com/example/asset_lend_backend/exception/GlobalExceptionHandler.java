package com.example.asset_lend_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.asset_lend_backend.response.ApiResponse;

import jakarta.validation.constraints.Null;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ApiResponse<Null>> handleException(Exception e) {
        return ApiResponse.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}