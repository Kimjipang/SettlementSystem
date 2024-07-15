package com.example.settlement.advertisement.repository.read;

import com.example.settlement.advertisement.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
public interface VideoAdReadRepository extends JpaRepository<VideoAd, Long> {
    List<VideoAd> findAllByVideoId(Long id);
}
