package com.example.settlement.advertisement.entity;

import com.example.settlement.common.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ad_statistics")
public class AdStatistics extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_stat_id")
    private Long id;

    @Column(nullable = false)
    private int daily_view;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_ad_id")
    private VideoAd videoAd;


    public AdStatistics(int daily_view, LocalDate date, VideoAd videoAd) {
        this.daily_view = daily_view;
        this.date = date;
        this.videoAd = videoAd;
    }
}
