package com.example.asset_lend_backend.controller;

import com.example.asset_lend_backend.response.ApiResponse;
import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> index() {
        return ResponseEntity.ok(new ApiResponse<>(true, service.findAll(), "Users found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> show(@PathVariable Long id) {
        return service.findById(id)
                .map(user -> new ApiResponse<>(true, user, "User found"))
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, null, "User not found")));
    }

    // @PostMapping
    // public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
    // User createdUser = service.createUser(user);
    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(new ApiResponse<>(true, createdUser, "User created successfully"));
    // }
}