package com.example.settlement.user.dto.request;

import com.example.settlement.user.entity.Role;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private Role role;

}
