package com.example.settlement.advertisement.controller;


import com.example.settlement.advertisement.dto.request.AdAdjustmentRequestDto;
import com.example.settlement.advertisement.dto.response.AdAdjustmentResponseDto;
import com.example.settlement.advertisement.service.AdAdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ad/adjustment")
public class AdAdjustmentController {

    private final AdAdjustmentService adAdjustmentService;

    @PostMapping
    public ResponseEntity<AdAdjustmentResponseDto> calculateDailyAd(@RequestBody AdAdjustmentRequestDto adAdjustmentRequestDto) {
        AdAdjustmentResponseDto adAdjustmentResponseDto = adAdjustmentService.calculateDailyAd(adAdjustmentRequestDto);
        return new ResponseEntity<AdAdjustmentResponseDto>(adAdjustmentResponseDto, HttpStatus.CREATED);
    }


}
