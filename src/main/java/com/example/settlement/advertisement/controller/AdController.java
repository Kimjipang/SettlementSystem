package com.example.settlement.advertisement.controller;


import com.example.settlement.advertisement.dto.request.AdRequestDto;
import com.example.settlement.advertisement.dto.response.AdResponseDto;
import com.example.settlement.advertisement.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ad")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @PostMapping
    ResponseEntity<AdResponseDto> createAd(@RequestBody AdRequestDto adRequestDto) {
        AdResponseDto adResponseDto = adService.createAd(adRequestDto);
        ResponseEntity<AdResponseDto> response = new ResponseEntity<>(adResponseDto, HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    ResponseEntity<List<AdResponseDto>> getAdList() {
        List<AdResponseDto> adResponseDtoList = adService.getAdList();
        ResponseEntity<List<AdResponseDto>> response = new ResponseEntity<>(adResponseDtoList, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    ResponseEntity<AdResponseDto> getAd(@PathVariable Long id) {
        AdResponseDto adResponseDto = adService.getAd(id);
        ResponseEntity<AdResponseDto> response = new ResponseEntity<>(adResponseDto, HttpStatus.OK);
        return response;
    }
}
