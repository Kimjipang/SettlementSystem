package com.example.settlement.video.controller;

import com.example.settlement.video.dto.request.VideoViewRequestDto;
import com.example.settlement.video.dto.response.VideoViewResponseDto;
import com.example.settlement.video.entity.VideoView;
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
        /*
        동영상 조회 기록 생성 API -> 동영상이 종료될 때 호출됨.
        1. jwt 토큰으로 유저 검증
        2. 요청한 사람의 user_id와 종료하고자 하는 video_id를 외래키로 가지는 VideoView 객체가 있으면 수정을 한다. 없으면, 새로 생성을 한다.
        */
        VideoView videoView = videoViewService.createVideoView(videoViewRequestDto);
        VideoViewResponseDto videoViewResponseDto = new VideoViewResponseDto(videoView);
        ResponseEntity<VideoViewResponseDto> reponse = new ResponseEntity<>(videoViewResponseDto, HttpStatus.CREATED);
        return reponse;
    }


    @DeleteMapping("/{id}")
    public void deleteVideoView(@PathVariable Long id) {
        videoViewService.deleteVideoView(id);
    }

}
