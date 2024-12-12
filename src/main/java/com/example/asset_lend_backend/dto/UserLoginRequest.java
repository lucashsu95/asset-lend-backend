package com.example.asset_lend_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserLoginRequest {
    
    @NotNull(message = "不得是空值")
    @Email(message = "格式不正確")
    @NotBlank(message = "不得是空值")
    String email;

    @NotNull(message = "不得是空值")
    @NotBlank(message = "不得是空值")
    String password;
}
