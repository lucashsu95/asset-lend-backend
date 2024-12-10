package com.example.asset_lend_backend.dto;

import com.example.asset_lend_backend.model.User.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String nickname;
    private UserType type;
    private String email;
    private String access_token;
}
