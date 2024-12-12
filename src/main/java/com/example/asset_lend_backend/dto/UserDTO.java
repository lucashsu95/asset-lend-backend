package com.example.asset_lend_backend.dto;

import com.example.asset_lend_backend.model.User;
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

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.type = user.getType();
    }
}