package com.example.settlement.advertisement.controller;


import com.example.settlement.advertisement.dto.request.AdViewRequestDto;
import com.example.settlement.advertisement.dto.response.AdViewResponseDto;
import com.example.settlement.advertisement.service.AdViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adview")
@RequiredArgsConstructor
public class AdViewController {

    private final AdViewService adViewService;



}
