package com.example.settlement.video.repository.read;

import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface VideoAdjustmentReadRepository extends JpaRepository<VideoAdjustment, Long> {

    List<VideoAdjustment> findByVideoAndDateBetween(Video video, LocalDate startDate, LocalDate endDate);
}
