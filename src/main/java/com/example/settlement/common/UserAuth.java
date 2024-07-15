package com.example.settlement.common;

import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.read.UserReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuth {

    private final UserReadRepository userReadRepository;

    public String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        return email;
    }

    public User getUserByIdAndValidate(Long userId, String email) {
        User user = userReadRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        // 사용자 검증 로직
        if (!user.getEmail().equals(email)) {
            throw new RuntimeException("로그인한 유저와 요청한 유저가 다릅니다.");
        }
        return user;
    }
}