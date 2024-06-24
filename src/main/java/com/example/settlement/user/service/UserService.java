package com.example.settlement.user.service;

import com.example.settlement.user.dto.request.UserRequestDto;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return user;
    }

    public User registerUser(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                encodedPassword
                );
        return userRepository.save(user);
    }

    public User updateUser(UserRequestDto userRequestDto, Long id) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.updateUser(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                encodedPassword
        );
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userRepository.delete(user);
    }
}
