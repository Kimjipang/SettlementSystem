package com.example.settlement.user.service;

import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
}
