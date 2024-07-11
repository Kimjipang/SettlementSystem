package com.example.settlement.video.entity;


import com.example.settlement.common.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoAdjustment extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_adjustment_id")
    private Long id;

    @Column(nullable = false)
    private BigDecimal daily_amount;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    public VideoAdjustment(BigDecimal daily_amount, LocalDate date, Video video) {
        this.daily_amount = daily_amount;
        this.date = date;
        this.video = video;
    }

}
