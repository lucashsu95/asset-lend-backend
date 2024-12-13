package com.example.asset_lend_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.asset_lend_backend.dto.UserDTOWithToken;
import com.example.asset_lend_backend.dto.UserLoginRequest;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.response.ApiResponse;
import com.example.asset_lend_backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    
    @Autowired
    private UserService service;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTOWithToken>> login(@RequestBody UserLoginRequest request) {
        UserDTOWithToken user = service.login(request);
        if (user == null) {
            return ApiResponse.INVALID_LOGIN();
        }
        return ApiResponse.success(user);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Null>> logout(HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        service.logout(currentUser);
        return ApiResponse.success(null);
    }
}
