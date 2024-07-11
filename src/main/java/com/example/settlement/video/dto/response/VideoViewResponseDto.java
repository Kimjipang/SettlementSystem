package com.example.settlement.video.dto.response;

import com.example.settlement.video.entity.VideoView;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class VideoViewResponseDto {
    private Long id;
    private Long user_id;
    private Long video_id;
    private LocalDate date;
    private int save_point;
    private LocalDateTime created_time;


    public VideoViewResponseDto(VideoView videoView) {
        this.id = videoView.getId();
        this.date = videoView.getDate();
        this.save_point = videoView.getSave_point();
        this.user_id = videoView.getUser().getId();
        this.video_id = videoView.getVideo().getId();
        this.created_time = videoView.getCreatedDate();
    }

    public VideoViewResponseDto(VideoView videoView, int ad_count) {
        this.id = videoView.getId();
        this.save_point = videoView.getSave_point();
        this.user_id = videoView.getUser().getId();
        this.video_id = videoView.getVideo().getId();
    }
}
