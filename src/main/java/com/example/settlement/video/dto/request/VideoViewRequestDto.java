package com.example.settlement.video.dto.request;


import lombok.Getter;

@Getter
public class VideoViewRequestDto {
    private int save_point;
    private int view_count;
    private Long user_id;
    private Long video_id;
}
