package com.example.asset_lend_backend.mapper;

import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setType(user.getType());
        return dto;
    }

    public static UserDTO toDTOWithToken(User user) {
        UserDTO dto = toDTO(user);
        dto.setAccess_token(user.getAccess_token());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setType(dto.getType());
        return user;
    }
}