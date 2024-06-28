package com.example.settlement.video.dto.request;


import lombok.Getter;

@Getter
public class VideoRequestDto {
    private String title;
    private int playing_time;
    private Long user_id;
}
