package com.example.settlement.advertisement.repository;

import com.example.settlement.advertisement.entity.Advertisement;
import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VideoAdRepository extends JpaRepository<VideoAd, Long> {
    Optional<VideoAd> findByVideoAndAdvertisement(Video video, Advertisement advertisement);
    List<VideoAd> findAllByVideoId(Long id);
    List<VideoAd> findAllByVideoIdAndLastModifiedDateLessThanEqual(Long id, LocalDateTime lastModifiedDate);
    List<VideoAd> findAllByVideoIdAndLastModifiedDateBetween(Long id, LocalDateTime start, LocalDateTime end);
}
