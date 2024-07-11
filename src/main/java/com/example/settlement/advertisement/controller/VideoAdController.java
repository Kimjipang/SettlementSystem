package com.example.settlement.advertisement.controller;


import com.example.settlement.advertisement.dto.request.VideoAdRequestDto;
import com.example.settlement.advertisement.dto.response.VideoAdResponseDto;
import com.example.settlement.advertisement.service.VideoAdService;
import com.example.settlement.video.dto.response.VideoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videoad")
public class VideoAdController {

    private final VideoAdService videoAdService;

    @PostMapping
    ResponseEntity<VideoAdResponseDto> createVideoAd(@RequestBody VideoAdRequestDto videoAdRequestDto) {
        VideoAdResponseDto videoAdResponseDto = videoAdService.createVideoAd(videoAdRequestDto);
        ResponseEntity<VideoAdResponseDto> response = new ResponseEntity<>(videoAdResponseDto, HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    ResponseEntity<List<VideoAdResponseDto>> getVideoAdList() {
        List<VideoAdResponseDto> videoAdResponseDtoList = videoAdService.getVideoAdList();
        ResponseEntity<List<VideoAdResponseDto>> response = new ResponseEntity<>(videoAdResponseDtoList, HttpStatus.OK);
        return response;
    }
}
