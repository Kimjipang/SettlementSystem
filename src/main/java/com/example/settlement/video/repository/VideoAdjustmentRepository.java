package com.example.settlement.video.repository;


import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VideoAdjustmentRepository extends JpaRepository<VideoAdjustment, Long> {

    List<VideoAdjustment> findByVideoAndDateBetween(Video video, LocalDate startDate, LocalDate endDate);
}
