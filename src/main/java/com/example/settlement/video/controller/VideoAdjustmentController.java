package com.example.settlement.video.controller;


import com.example.settlement.video.dto.request.VideoAdjustmentRequestDto;
import com.example.settlement.video.dto.response.VideoAdjustmentResponseDto;
import com.example.settlement.video.service.VideoAdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video/adjustment")
public class VideoAdjustmentController {

    private final VideoAdjustmentService videoAdjustmentService;

    @PostMapping
    public ResponseEntity<VideoAdjustmentResponseDto> calculateDailyVideo(@RequestBody VideoAdjustmentRequestDto videoAdjustmentRequestDto) {
        VideoAdjustmentResponseDto videoAdjustmentResponseDto = videoAdjustmentService.calculateDailyVideo(videoAdjustmentRequestDto);
        ResponseEntity<VideoAdjustmentResponseDto> response = new ResponseEntity<>(videoAdjustmentResponseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping
    public void todayDate() {
        LocalDate today = LocalDate.now().minusDays(1);
        System.out.println(today);
    }
}
