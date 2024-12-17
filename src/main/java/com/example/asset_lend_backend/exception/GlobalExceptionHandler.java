package com.example.asset_lend_backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.asset_lend_backend.response.ApiResponse;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 處理 URL 參數缺失的情況
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams() {
        return ApiResponse.MISSING_FIELD();
    }

    // 處理方法參數類型不匹配的情況
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch() {
        return ApiResponse.WRONG_DATA_TYPE();
    }

    // 處理請求體驗證失敗的情況
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // 根據錯誤類型返回不同的錯誤響應
        if (errors.values().stream().anyMatch(msg -> msg.contains("不得是空值"))) {
            return ApiResponse.MISSING_FIELD();
        }
        if (errors.values().stream().anyMatch(msg -> msg.contains("格式不正確"))) {
            return ApiResponse.WRONG_DATA_TYPE();
        }
        return ResponseEntity.badRequest().body(errors); // 其他錯誤
    }

    // 處理約束違反的情況
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException() {
        return ApiResponse.MISSING_FIELD();
    }

    // 處理數據完整性違反的情況
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("Duplicate entry") && message.contains("for key 'users.UK6dotkott2kjsp8vw4d0m25fb7'")) {
            return ApiResponse.USER_EXISTS();
        }
        return ApiResponse.fail("Data integrity violation: " + message, HttpStatus.CONFLICT.value());
    }
}