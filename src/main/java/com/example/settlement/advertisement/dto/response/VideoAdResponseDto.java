package com.example.settlement.advertisement.dto.response;

import com.example.settlement.advertisement.entity.Advertisement;
import com.example.settlement.advertisement.entity.VideoAd;
import lombok.Getter;

@Getter
public class VideoAdResponseDto {
    private Long video_ad_id;
    private int ad_position;
    private int ad_count;
    private Long video_id;
    private Long ad_id;
    private String ad_title;

    public VideoAdResponseDto(VideoAd videoAd) {
        this.video_ad_id = videoAd.getId();
        this.ad_position = videoAd.getAd_position();
        this.ad_count = videoAd.getAd_count();
        this.video_id = videoAd.getVideo().getId();
        this.ad_id = videoAd.getAdvertisement().getId();
    }

    public VideoAdResponseDto(VideoAd videoAd, String ad_title) {
        this.video_ad_id = videoAd.getId();
        this.ad_position = videoAd.getAd_position();
        this.ad_count = videoAd.getAd_count();
        this.video_id = videoAd.getVideo().getId();
        this.ad_id = videoAd.getAdvertisement().getId();
        this.ad_title = ad_title;
    }
}
