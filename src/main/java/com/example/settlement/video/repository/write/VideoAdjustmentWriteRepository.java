package com.example.settlement.video.repository.write;

import com.example.settlement.video.entity.VideoAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdjustmentWriteRepository extends JpaRepository<VideoAdjustment, Long> {
}
