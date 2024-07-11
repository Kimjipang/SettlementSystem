package com.example.settlement.advertisement.service;

import com.example.settlement.advertisement.dto.response.AdViewResponseDto;
import com.example.settlement.advertisement.entity.AdView;
import com.example.settlement.advertisement.repository.AdViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdViewService {

    private final AdViewRepository adViewRepository;

    public AdViewResponseDto getAdView(Long adViewId) {
        AdView adView = adViewRepository.findById(adViewId).orElseThrow(
                () -> new RuntimeException("찾고자 하는 조회 기록이 없습니다.")
        );
        return new AdViewResponseDto(adView);
    }
}
