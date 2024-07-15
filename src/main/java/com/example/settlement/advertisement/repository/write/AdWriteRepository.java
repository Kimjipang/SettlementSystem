package com.example.settlement.advertisement.repository.write;

import com.example.settlement.advertisement.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdWriteRepository extends JpaRepository<Advertisement, Long> {
}
