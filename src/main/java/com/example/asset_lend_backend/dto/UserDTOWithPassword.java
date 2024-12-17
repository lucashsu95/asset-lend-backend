package com.example.asset_lend_backend.dto;

import com.example.asset_lend_backend.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTOWithPassword extends UserDTO {

    @NotNull
    @NotBlank(message = "不得是空值")
    private String password;

    public UserDTOWithPassword() {
        super();
    }

    public UserDTOWithPassword(User user) {
        super(user);
        this.password = user.getPassword();
    }
}
