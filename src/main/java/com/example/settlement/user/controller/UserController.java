package com.example.settlement.user.controller;

import com.example.settlement.user.dto.request.AuthenticationRequest;
import com.example.settlement.user.dto.request.UserRequestDto;
import com.example.settlement.user.dto.response.AuthenticationResponse;
import com.example.settlement.user.dto.response.UserResponseDto;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.service.CustomUserDetailsService;
import com.example.settlement.user.service.UserService;
import com.example.settlement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        ResponseEntity<UserResponseDto> response = new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.registerUser(userRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        ResponseEntity<UserResponseDto> response = new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        User updatedUser = userService.updateUser(userRequestDto, id);
        UserResponseDto userResponseDto = new UserResponseDto(updatedUser);
        ResponseEntity<UserResponseDto> response = new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
