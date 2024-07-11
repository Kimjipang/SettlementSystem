package com.example.settlement.video.controller;


import com.example.settlement.video.dto.request.VideoRequestDto;
import com.example.settlement.video.dto.response.VideoResponseDto;
import com.example.settlement.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;


    @PostMapping
    public ResponseEntity<VideoResponseDto> createVideo(@RequestBody VideoRequestDto videoRequestDto) {
        VideoResponseDto videoResponseDto = videoService.createVideo(videoRequestDto);
        return new ResponseEntity<>(videoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VideoResponseDto>> getVideoList() {
        List<VideoResponseDto> videoResponseDtoList = videoService.getVideoList();
        return new ResponseEntity<>(videoResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDto> getVideo(@PathVariable Long id) {
        VideoResponseDto videoResponseDto = videoService.getVideo(id);
        return new ResponseEntity<>(videoResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoResponseDto> updateVideo(@PathVariable Long id, @RequestBody VideoRequestDto videoRequestDto) {
        VideoResponseDto videoResponseDto = videoService.updateVideo(id, videoRequestDto);
        return new ResponseEntity<>(videoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        ResponseEntity<String> response = new ResponseEntity<>("비디오가 정상적으로 삭제되었습니다.", HttpStatus.OK);
        return response;
    }
}
