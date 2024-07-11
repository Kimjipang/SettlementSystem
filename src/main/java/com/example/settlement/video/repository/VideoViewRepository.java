package com.example.settlement.video.repository;

import com.example.settlement.user.entity.User;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VideoViewRepository extends JpaRepository<VideoView, Long> {
    List<VideoView> findAllByVideoId(Long videoId);
    Optional<VideoView> findByVideoAndUser(Video video, User user);

    List<VideoView> findAllByVideoIdAndDate(Long videoId, LocalDate date);

    @Query(value = "SELECT v.save_point FROM video_view v WHERE v.user_id = :userId AND v.video_id = :videoId ORDER BY v.created_date DESC LIMIT 1", nativeQuery = true)
    Optional<Integer> findLatestSavePoint(@Param("userId") Long userId, @Param("videoId") Long videoId);

    @Query(value = "SELECT COUNT(*) FROM video_view vw " +
            "JOIN video v ON vw.video_id = v.video_id " +
            "WHERE vw.video_id = :videoId " +
            "AND vw.date = :date " +
            "AND v.user_id <> vw.user_id", nativeQuery = true)
    Integer countByVideoIdAndDateAndDifferentUser(@Param("videoId") Long videoId, @Param("date") LocalDate date);

}