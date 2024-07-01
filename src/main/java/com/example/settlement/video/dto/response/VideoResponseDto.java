package com.example.settlement.video.dto.response;

import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.video.entity.Video;
import lombok.Getter;

@Getter
public class VideoResponseDto {
    private Long id;
    private String title;
    private int playing_time;
    private int save_point;
    private Long user_id;
    private VideoAd videoAd;

    public VideoResponseDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.playing_time = video.getPlaying_time();
        this.user_id = video.getUser().getId();
    }

    public VideoResponseDto(Video video, int save_point) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.playing_time = video.getPlaying_time();
        this.save_point = save_point;
        this.user_id = video.getUser().getId();
    }
}
