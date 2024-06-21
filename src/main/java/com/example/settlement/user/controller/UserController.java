package com.example.settlement.user.controller;

import com.example.settlement.user.dto.UserResponseDto;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUserList() {
        List<User> userList = userService.getUserList();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            userResponseDtoList.add(new UserResponseDto(user));
        }
        ResponseEntity<List<UserResponseDto>> response = new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        ResponseEntity<UserResponseDto> response = new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        return response;
    }
}
