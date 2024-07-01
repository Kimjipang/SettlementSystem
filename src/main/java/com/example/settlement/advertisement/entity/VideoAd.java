package com.example.settlement.advertisement.entity;

import com.example.settlement.common.BaseTimeEntity;
import com.example.settlement.video.entity.Video;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoAd extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoAd_id")
    private Long id;

    @Column(nullable = false)
    private int ad_position;

    @Column(nullable = false)
    private int ad_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Advertisement advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;


    public VideoAd(int ad_position, int ad_count, Video video, Advertisement advertisement) {
        this.ad_position = ad_position;
        this.ad_count = ad_count;
        this.video = video;
        this.advertisement = advertisement;
    }

    public void videoAdCount() {
        this.ad_count++;
    }
}
