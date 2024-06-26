package com.example.settlement.video.repository;


import com.example.settlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUserId(Long id);
    Optional<Video> findByIdAndUserId(Long id, Long userId);
}
