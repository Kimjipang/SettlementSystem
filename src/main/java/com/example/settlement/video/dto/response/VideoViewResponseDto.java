package com.example.settlement.video.dto.response;

import com.example.settlement.video.entity.VideoView;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VideoViewResponseDto {
    private Long id;
    private int save_point;
    private int view_count;
    private Long user_id;
    private Long video_id;
    private int ad_count;
    private LocalDateTime created_time;
    private LocalDateTime lastModified_time;


    public VideoViewResponseDto(VideoView videoView) {
        this.id = videoView.getId();
        this.save_point = videoView.getSave_point();
        this.view_count = videoView.getView_count();
        this.user_id = videoView.getUser().getId();
        this.video_id = videoView.getVideo().getId();
        this.created_time = videoView.getCreatedDate();
        this.lastModified_time = videoView.getLastModifiedDate();
    }

    public VideoViewResponseDto(VideoView videoView, int ad_count) {
        this.id = videoView.getId();
        this.save_point = videoView.getSave_point();
        this.view_count = videoView.getView_count();
        this.user_id = videoView.getUser().getId();
        this.video_id = videoView.getVideo().getId();
        this.created_time = videoView.getCreatedDate();
        this.lastModified_time = videoView.getLastModifiedDate();
    }
}
