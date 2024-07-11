package com.example.settlement.video.repository;


import com.example.settlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT v.id FROM Video v")
    List<Long> findAllIds();
    List<Video> findAllByUserId(Long id);
    Optional<Video> findByIdAndUserId(Long id, Long userId);
}
