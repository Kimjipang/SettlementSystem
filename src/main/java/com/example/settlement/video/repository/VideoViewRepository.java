package com.example.settlement.video.repository;

import com.example.settlement.user.entity.User;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoViewRepository extends JpaRepository<VideoView, Long> {

    Optional<VideoView> findByVideoAndUser(Video video, User user);
}
