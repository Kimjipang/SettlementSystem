package com.example.settlement.advertisement.entity;

import com.example.settlement.common.BaseCreateTimeEntity;
import com.example.settlement.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdAdjustment extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_adjustment_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    BigDecimal daily_amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_ad_id")
    private VideoAd videoAd;


    public AdAdjustment(LocalDate date, BigDecimal daily_amount, VideoAd videoAd) {
        this.date = date;
        this.daily_amount = daily_amount;
        this.videoAd = videoAd;
    }
}
