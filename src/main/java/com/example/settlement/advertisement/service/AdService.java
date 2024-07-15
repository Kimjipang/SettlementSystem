package com.example.settlement.advertisement.service;


import com.example.settlement.advertisement.dto.request.AdRequestDto;
import com.example.settlement.advertisement.dto.response.AdResponseDto;
import com.example.settlement.advertisement.entity.Advertisement;
import com.example.settlement.advertisement.repository.read.AdReadRepository;
import com.example.settlement.advertisement.repository.write.AdWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdService {

    private final AdReadRepository adReadRepository;
    private final AdWriteRepository adWriteRepository;

    public AdResponseDto createAd(AdRequestDto adRequestDto) {
        Advertisement ad = new Advertisement(adRequestDto.getTitle());
        return new AdResponseDto(adWriteRepository.save(ad));
    }

    public List<AdResponseDto> getAdList() {
        List<Advertisement> adList = adReadRepository.findAll();
        List<AdResponseDto> adResponseDtoList = new ArrayList<>();
        for (Advertisement advertisement : adList) {
            adResponseDtoList.add(new AdResponseDto(advertisement));
        }
        return adResponseDtoList;
    }

    public AdResponseDto getAd(Long id) {
        Advertisement advertisement = adReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("찾는 광고가 없습니다.")
        );
        return new AdResponseDto(advertisement);
    }
}
