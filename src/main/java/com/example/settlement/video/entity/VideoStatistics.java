package com.example.settlement.video.entity;

import com.example.settlement.common.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoStatistics extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_stat_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int daily_view;

    @Column(nullable = false)
    private long total_playing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;


    public VideoStatistics(LocalDate date, int daily_view, long total_playing, Video video) {
        this.date = date;
        this.daily_view = daily_view;
        this.total_playing = total_playing;
        this.video = video;
    }
}
