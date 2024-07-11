package com.example.settlement.advertisement.dto.response;

import com.example.settlement.advertisement.entity.AdAdjustment;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AdAdjustmentResponseDto {
    private long id;
    private LocalDate date;
    private BigDecimal daily_amount;
    private long videoAd_id;

    public AdAdjustmentResponseDto(AdAdjustment adAdjustment) {
        this.id = adAdjustment.getId();
        this.date = adAdjustment.getDate();
        this.daily_amount = adAdjustment.getDaily_amount();
        this.videoAd_id = adAdjustment.getVideoAd().getId();
    }
}
