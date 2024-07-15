package com.example.settlement.advertisement.repository.write;

import com.example.settlement.advertisement.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdWriteRepository extends JpaRepository<VideoAd, Long> {
}
