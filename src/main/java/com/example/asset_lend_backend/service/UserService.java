package com.example.asset_lend_backend.service;

import com.example.asset_lend_backend.config.SecurityConfig;
import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.dto.UserDTOWithPassword;
import com.example.asset_lend_backend.dto.UserDTOWithToken;
import com.example.asset_lend_backend.dto.UserLoginRequest;
import com.example.asset_lend_backend.mapper.UserMapper;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() throws Exception {
        return repo.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO save(UserDTOWithPassword UserDTOWithPassword) {
        User user = userMapper.toEntity(UserDTOWithPassword);
        user.setPasswordHash(passwordEncoder.encode(UserDTOWithPassword.getPassword()));
        repo.save(user);
        return userMapper.toDTO(user);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) throws Exception {
        repo.deleteById(id);
    }

    public UserDTOWithToken login(UserLoginRequest request) {
        User user = repo.findByEmail(request.getEmail());
        if (user == null) {
            return null;
        }

        if (!securityConfig.checkPassword(request.getPassword(), user.getPasswordHash())) {
            return null;
        }

        String token = securityConfig.generateToken(user.getEmail());
        user.setAccess_token(token);
        repo.save(user);

        return userMapper.toDTOWithToken(user);
    }

    public void logout(User currentUser) {
        currentUser.setAccess_token(null);
        repo.save(currentUser);
    }
}