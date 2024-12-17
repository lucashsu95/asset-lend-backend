package com.example.asset_lend_backend.dto;

import com.example.asset_lend_backend.model.User;
import com.example.asset_lend_backend.model.User.UserType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotNull
    @NotBlank(message = "不得是空值")
    private String nickname;
    
    @NotNull(message = "不得是空值")
    private UserType type;
    
    @NotNull
    @NotBlank(message = "不得是空值")
    private String email;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.type = user.getType();
    }
}