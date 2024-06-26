package com.example.settlement.video.dto.response;

import com.example.settlement.video.entity.Video;
import lombok.Getter;

@Getter
public class VideoResponseDto {
    private Long id;
    private String title;
    private int playing_time;
    private Long user_id;

    public VideoResponseDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.playing_time = video.getPlaying_time();
        this.user_id = video.getUser().getId();
    }
}
