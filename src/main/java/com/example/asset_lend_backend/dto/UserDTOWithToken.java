package com.example.asset_lend_backend.dto;

import com.example.asset_lend_backend.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTOWithToken extends UserDTO {

    private String access_token;

    public UserDTOWithToken() {
        super();
    }

    public UserDTOWithToken(User user) {
        super(user);
        access_token = user.getAccess_token();
    }
}
