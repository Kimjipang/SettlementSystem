package com.example.settlement.advertisement.service;

import com.example.settlement.advertisement.dto.response.AdViewResponseDto;
import com.example.settlement.advertisement.entity.AdView;
import com.example.settlement.advertisement.repository.read.AdViewReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdViewService {

    private final AdViewReadRepository adViewReadRepository;

    public AdViewResponseDto getAdView(Long adViewId) {
        AdView adView = adViewReadRepository.findById(adViewId).orElseThrow(
                () -> new RuntimeException("찾고자 하는 조회 기록이 없습니다.")
        );
        return new AdViewResponseDto(adView);
    }
}
