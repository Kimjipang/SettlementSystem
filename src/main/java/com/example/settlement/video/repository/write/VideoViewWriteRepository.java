package com.example.settlement.video.repository.write;

import com.example.settlement.video.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoViewWriteRepository extends JpaRepository<VideoView, Long> {
}
