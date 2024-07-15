package com.example.settlement.user.repository.write;

import com.example.settlement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserWriteRepository extends JpaRepository<User, Integer> {
}
