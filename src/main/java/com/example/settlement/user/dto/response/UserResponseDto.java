package com.example.settlement.user.dto.response;

import com.example.settlement.user.entity.User;
import lombok.Getter;


@Getter
public class UserResponseDto {
    private long id;
    private String username;
    private String email;
    private String password;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
