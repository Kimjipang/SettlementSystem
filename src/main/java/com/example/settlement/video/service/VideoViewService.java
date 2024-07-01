package com.example.settlement.video.service;


import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.advertisement.repository.VideoAdRepository;
import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.video.dto.request.VideoViewRequestDto;
import com.example.settlement.video.dto.response.VideoViewResponseDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoView;
import com.example.settlement.video.repository.VideoRepository;
import com.example.settlement.video.repository.VideoViewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoViewService {

    private final VideoViewRepository videoViewRepository;
    private final VideoRepository videoRepository;
    private final UserAuth userAuth;
    private final VideoAdRepository videoAdRepository;

    @Transactional
    public VideoViewResponseDto createVideoView(VideoViewRequestDto videoViewRequestDto) {
        /*
        1. 영상을 껐을 때, VideoView 객체를 생성하는 API를 호출한다.
        2. 이때, VideoView 객체를 생성하는 유저가(요청 데이터에 있는 user_id) jwt 내의 저장된 유저 정보와 같으면, 조회 카운팅 X
        3. 반대면, 다른 사람이 Video를 조회한 것이기에 카운팅을 한다.
        4.
         */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoViewRequestDto.getUser_id(), email);
        Video video = videoRepository.findById(videoViewRequestDto.getVideo_id()).orElseThrow(
                () -> new RuntimeException("기록을 조회할 영상이 존재하지 않습니다.")
        );

        VideoView videoView = videoViewRepository.findByVideoAndUser(video, user)
                .orElseGet(() -> new VideoView(0, 0, video, user));

        int beforeWatched = videoView.getSave_point(); // 동영상을 재생했을 때의 재생 시점
        int afterWatched = videoViewRequestDto.getSave_point(); // 동영상 중단했을 때의 재생 시점

        // 요청한 사용자와 video를 생성한 유저가 같다면, 조회수 카운팅 X
        if(video.getUser().getId().equals(user.getId())) {
            videoView.updateSavePoint(afterWatched);
        }
        else {
            videoView.updateSavePoint(afterWatched);
            videoView.increase_view_count();
        }

        List<VideoAd> videoAdList = videoAdRepository.findAllByVideoId(video.getId());

        if(!video.getUser().getId().equals(user.getId())) {
            for (VideoAd videoAd : videoAdList) {
                if (beforeWatched < videoAd.getAd_position() && afterWatched >= videoAd.getAd_position()) {
                    videoAd.videoAdCount();
                    videoAdRepository.save(videoAd);
                }
            }
        }
        VideoView savedVideoView = videoViewRepository.save(videoView);
        return new VideoViewResponseDto(savedVideoView);
    }

    public void deleteVideoView(Long id) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(id, email);

    }
}
