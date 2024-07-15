package com.example.settlement.video.repository.write;

import com.example.settlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoWriteRepository extends JpaRepository<Video, Long> {
}
