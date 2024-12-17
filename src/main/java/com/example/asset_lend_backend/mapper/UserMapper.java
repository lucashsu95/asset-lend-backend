package com.example.asset_lend_backend.mapper;

import com.example.asset_lend_backend.dto.UserDTO;
import com.example.asset_lend_backend.dto.UserDTOWithPassword;
import com.example.asset_lend_backend.dto.UserDTOWithToken;
import com.example.asset_lend_backend.model.User;

import org.springframework.stereotype.Component;
@Component
public class UserMapper {


    public UserDTO toDTO(User user) {
        return new UserDTO(user);
    }

    public UserDTOWithToken toDTOWithToken(User user) {
        UserDTOWithToken dto = new UserDTOWithToken(user);
        return dto;
    }

    public UserDTOWithPassword toDTOWithPassword(User user){
        UserDTOWithPassword dto = new UserDTOWithPassword(user);
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setType(dto.getType());
        return user;
    }
}