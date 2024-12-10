package com.example.asset_lend_backend.service;

import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.mapper.UserMapper;
import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void save(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}