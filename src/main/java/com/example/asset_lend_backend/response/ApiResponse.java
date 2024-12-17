package com.example.asset_lend_backend.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

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
        return success(data, 200);
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(String message) {
        return fail(message, 400);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data, Integer statusCode) {
        if (statusCode == null) {
            statusCode = 200;
        }
        return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiResponse<T>(true, data, ""));
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, Integer statusCode) {
        if (statusCode == null) {
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiResponse<T>(false, (T) null, message));
    };

    public static <T> ResponseEntity<ApiResponse<T>> USER_NOT_FOUND() {
        return fail("MSG_USER_NOT_FOUND", 404);
    }

    public static <T> ResponseEntity<ApiResponse<T>> INVALID_LOGIN() {
        return fail("MSG_INVALID_LOGIN");
    }

    public static <T> ResponseEntity<ApiResponse<T>> USER_EXISTS() {
        return fail("MSG_USER_EXISTS", 409);
    }

    public static <T> ResponseEntity<ApiResponse<T>> USER_NOT_EXISTS() {
        return fail("MSG_USER_NOT_EXISTS", 404);
    }

    public static <T> ResponseEntity<ApiResponse<T>> INVALID_ACCESS_TOKEN() {
        return fail("MSG_INVALID_ACCESS_TOKEN", 401);
    }

    public static <T> ResponseEntity<ApiResponse<T>> PERMISSION_DENY() {
        return fail("MSG_PERMISSION_DENY", 403);
    }

    public static <T> ResponseEntity<ApiResponse<T>> MISSING_FIELD() {
        return fail("MSG_MISSING_FIELD");
    }

    public static <T> ResponseEntity<ApiResponse<T>> WRONG_DATA_TYPE() {
        return fail("MSG_WRONG_DATA_TYPE");
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{\"success\":false,\"message\":\"Error converting response to JSON\"}";
        }
    }
}
