package com.example.settlement.video.repository.read;


import com.example.settlement.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VideoReadRepository extends JpaRepository<Video, Long> {

    List<Video> findAllByUserId(Long id);
    Optional<Video> findByIdAndUserId(Long id, Long userId);
}

