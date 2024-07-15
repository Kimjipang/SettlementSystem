package com.example.settlement.advertisement.service;


import com.example.settlement.advertisement.dto.request.VideoAdRequestDto;
import com.example.settlement.advertisement.dto.response.VideoAdResponseDto;
import com.example.settlement.advertisement.entity.Advertisement;
import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.advertisement.repository.read.AdReadRepository;
import com.example.settlement.advertisement.repository.read.VideoAdReadRepository;
import com.example.settlement.advertisement.repository.write.VideoAdWriteRepository;
import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.read.UserReadRepository;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.repository.read.VideoReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoAdService {

    private final VideoAdReadRepository videoAdReadRepository;
    private final VideoAdWriteRepository videoAdWriteRepository;
    private final VideoReadRepository videoReadRepository;
    private final AdReadRepository adReadRepository;
    private final UserAuth userAuth;
    private final UserReadRepository userReadRepository;


    public VideoAdResponseDto createVideoAd(VideoAdRequestDto videoAdRequestDto) {
        String email = userAuth.getAuthenticatedUserEmail();

        Video video = videoReadRepository.findById(videoAdRequestDto.getVideo_id()).orElseThrow(
                () -> new RuntimeException("광고를 넣을 동영상이 존재하지 않습니다.")
        );

        User user = userReadRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("해당 유저가 존재하지 않습니다.")
        );

        if(!user.getId().equals(video.getUser().getId())) {
            throw new RuntimeException("동영상에 대한 권한이 없는 유저입니다.");
        }

        Advertisement advertisement = adReadRepository.findById(videoAdRequestDto.getAd_id()).orElseThrow(
                () -> new RuntimeException("추가할 광고가 없습니다.")
        );

        // 같은 video_id와 ad_id를 가지는 VideoAd 객체 중 ad_position이 같은 것들이 있으면 안됨.

        VideoAd videoAd = new VideoAd(
                videoAdRequestDto.getAd_position(),
                0,
                video,
                advertisement
                );

        return new VideoAdResponseDto(videoAdWriteRepository.save(videoAd), advertisement.getTitle());
    }

    public List<VideoAdResponseDto> getVideoAdList() {
        List<VideoAd> videoAdList = videoAdReadRepository.findAll();
        List<VideoAdResponseDto> videoAdResponseDtoList = new ArrayList<>();

        for (VideoAd videoAd : videoAdList) {
            VideoAdResponseDto videoAdResponseDto = new VideoAdResponseDto(videoAd);
            videoAdResponseDtoList.add(videoAdResponseDto);
        }

        return videoAdResponseDtoList;
    }
}
