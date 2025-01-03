package com.example.asset_lend_backend.controller;

import com.example.asset_lend_backend.response.ApiResponse;
import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.dto.UserDTOWithPassword;
import com.example.asset_lend_backend.mapper.UserMapper;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> index() {
        try {
            return ApiResponse.success(service.findAll());
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage(), null);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> insert(@Valid @RequestBody UserDTOWithPassword userDTO) {
        try {
            UserDTO user = service.save(userDTO);
            return ApiResponse.success(user);
        } catch (DataIntegrityViolationException e) {
            return ApiResponse.USER_EXISTS();
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> show(@PathVariable Long id) {
        User user = service.findById(id).orElse(null);
        if (user == null) {
            return ApiResponse.USER_NOT_EXISTS();
        }
        return ApiResponse.success(new UserMapper().toDTO(user));
    }
}