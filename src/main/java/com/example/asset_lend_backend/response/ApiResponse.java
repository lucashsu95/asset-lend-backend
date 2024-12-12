package com.example.asset_lend_backend.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL) // 確保僅包含非空字段
@Getter
@Setter
public class ApiResponse<T> {
    private Boolean success;
    private T data;
    private String message;

    private ApiResponse(Boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return success(data, HttpStatus.OK);
    }
    
    public static <T> ResponseEntity<ApiResponse<T>> fail(String message) {
        return fail(message, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus statusCode) {
        return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiResponse<T>(true, data, ""));
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, HttpStatus statusCode) {
        return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiResponse<T>(false, (T) null, message));
    };

    public static <T> ResponseEntity<ApiResponse<T>> USER_NOT_FOUND() {
        return fail("MSG_USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
