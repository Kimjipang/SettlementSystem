package com.example.settlement.video.controller;

import com.example.settlement.video.dto.request.VideoViewRequestDto;
import com.example.settlement.video.dto.response.VideoViewResponseDto;
import com.example.settlement.video.service.VideoViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videoview")
public class VideoViewController {

    private final VideoViewService videoViewService;

    @PostMapping
    public ResponseEntity<VideoViewResponseDto> createVideoView(@RequestBody VideoViewRequestDto videoViewRequestDto) {
        VideoViewResponseDto videoViewResponseDto = videoViewService.createVideoView(videoViewRequestDto);
        ResponseEntity<VideoViewResponseDto> reponse = new ResponseEntity<>(videoViewResponseDto, HttpStatus.CREATED);
        return reponse;
    }


    @DeleteMapping("/{id}")
    public void deleteVideoView(@PathVariable Long id) {
        videoViewService.deleteVideoView(id);
    }

}
