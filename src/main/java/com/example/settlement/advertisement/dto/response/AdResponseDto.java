package com.example.settlement.advertisement.dto.response;

import com.example.settlement.advertisement.entity.Advertisement;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;

    public AdResponseDto(Advertisement advertisement) {
        this.id = advertisement.getId();
        this.title = advertisement.getTitle();
        this.createdAt = advertisement.getCreatedDate();

    }
}
