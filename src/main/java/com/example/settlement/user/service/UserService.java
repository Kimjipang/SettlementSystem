package com.example.settlement.user.service;

import com.example.settlement.user.dto.request.UserRequestDto;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.read.UserReadRepository;
import com.example.settlement.user.repository.write.UserWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserReadRepository userReadRepository;
    private final UserWriteRepository userWriteRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUserList() {
        List<User> userList = userReadRepository.findAll();
        return userList;
    }

    public User getUser(Long id) {
        User user = userReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return user;
    }

    public User registerUser(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                encodedPassword,
                userRequestDto.getRole()
                );
        return userWriteRepository.save(user);
    }

    public User updateUser(UserRequestDto userRequestDto, Long id) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = userReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.updateUser(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                encodedPassword,
                userRequestDto.getRole()
        );
        return userWriteRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userWriteRepository.delete(user);
    }
}
