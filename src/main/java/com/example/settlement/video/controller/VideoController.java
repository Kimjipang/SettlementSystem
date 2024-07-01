package com.example.settlement.video.controller;


import com.example.settlement.video.dto.request.VideoRequestDto;
import com.example.settlement.video.dto.response.VideoResponseDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoResponseDto> createVideo(@RequestBody VideoRequestDto videoRequestDto) {
        Video video = videoService.createVideo(videoRequestDto);
        VideoResponseDto videoResponseDto = new VideoResponseDto(video);
        return new ResponseEntity<>(videoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VideoResponseDto>> getVideoList() {
        List<Video> videoList = videoService.getVideoList();
        List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
        for (Video video : videoList) {
            VideoResponseDto videoResponseDto = new VideoResponseDto(video);
            videoResponseDtoList.add(videoResponseDto);
        }
        ResponseEntity<List<VideoResponseDto>> response = new ResponseEntity<>(videoResponseDtoList, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDto> getVideo(@PathVariable Long id) {
        VideoResponseDto videoResponseDto = videoService.getVideo(id);
        ResponseEntity<VideoResponseDto> response = new ResponseEntity<>(videoResponseDto, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoResponseDto> updateVideo(@PathVariable Long id, @RequestBody VideoRequestDto videoRequestDto) {
        Video updatedVideo = videoService.updateVideo(id, videoRequestDto);
        VideoResponseDto videoResponseDto = new VideoResponseDto(updatedVideo);
        ResponseEntity<VideoResponseDto> response = new ResponseEntity<>(videoResponseDto, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        ResponseEntity<String> response = new ResponseEntity<>("비디오가 정상적으로 삭제되었습니다.", HttpStatus.OK);
        return response;
    }
}
