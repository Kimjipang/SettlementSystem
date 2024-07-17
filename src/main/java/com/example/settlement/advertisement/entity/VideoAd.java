package com.example.settlement.advertisement.entity;

import com.example.settlement.common.BaseTimeEntity;
import com.example.settlement.video.entity.Video;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video_ad")
public class VideoAd extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_ad_id")
    private Long id;

    @Column(nullable = false)
    private int ad_position;

    @Column(nullable = false)
    private int view_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Advertisement advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @OneToMany(mappedBy = "videoAd")
    private List<AdView> adViewList = new ArrayList<>();


    public VideoAd(int ad_position, int view_count, Video video, Advertisement advertisement) {
        this.ad_position = ad_position;
        this.view_count = view_count;
        this.video = video;
        this.advertisement = advertisement;
    }

    public void videoAdCount(int view_count) {
        this.view_count = view_count + 1;
    }
}
