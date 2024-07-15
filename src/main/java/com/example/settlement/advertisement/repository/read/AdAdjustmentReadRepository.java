package com.example.settlement.advertisement.repository.read;

import com.example.settlement.advertisement.entity.AdAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Transactional(readOnly = true)
public interface AdAdjustmentReadRepository extends JpaRepository<AdAdjustment, Long> {

    @Query(value = "SELECT COUNT(*) FROM ad_view av " +
            "JOIN video_ad va ON av.video_ad_id = va.video_ad_id " +
            "WHERE av.video_ad_id = :videoAdId " +
            "AND av.date = :date " +
            "AND av.user_id <> :user_id", nativeQuery = true)
    int countTodayAdView(@Param("videoAdId") Long videoAdId, @Param("user_id") Long user_id, @Param("date") LocalDate date);
}
