package com.example.settlement.video.dto.response;

import com.example.settlement.video.entity.VideoAdjustment;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class VideoAdjustmentResponseDto {
    private Long id;
    private BigDecimal daily_amount;
    private LocalDate date;
    private Long video_id;
    private LocalDateTime created_date;

    public VideoAdjustmentResponseDto(VideoAdjustment videoAdjustment) {
        this.id = videoAdjustment.getId();
        this.daily_amount = videoAdjustment.getDaily_amount();
        this.date = videoAdjustment.getDate();
        this.video_id = videoAdjustment.getVideo().getId();
        this.created_date = videoAdjustment.getCreatedDate();
    }
}
