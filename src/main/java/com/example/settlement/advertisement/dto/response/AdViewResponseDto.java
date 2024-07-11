package com.example.settlement.advertisement.dto.response;

import com.example.settlement.advertisement.entity.AdView;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdViewResponseDto {
    private Long id;
    private Long user_id;
    private Long videoAd_id;
    private LocalDateTime created_at;


    public AdViewResponseDto(AdView adView) {
        this.id = adView.getId();
        this.user_id = adView.getUser().getId();
        this.videoAd_id = adView.getVideoAd().getId();
        this.created_at = adView.getCreatedDate();
    }
}
