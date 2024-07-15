package com.example.settlement.video.service;


import com.example.settlement.advertisement.entity.AdView;
import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.advertisement.repository.read.VideoAdReadRepository;
import com.example.settlement.advertisement.repository.write.AdViewWriteRepository;
import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.video.dto.request.VideoViewRequestDto;
import com.example.settlement.video.dto.response.VideoViewResponseDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoView;
import com.example.settlement.video.repository.read.VideoReadRepository;
import com.example.settlement.video.repository.read.VideoViewReadRepository;
import com.example.settlement.video.repository.write.VideoViewWriteRepository;
import com.example.settlement.video.repository.write.VideoWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoViewService {

    private final VideoViewReadRepository videoViewReadRepository;
    private final VideoViewWriteRepository videoViewWriteRepository;
    private final VideoReadRepository videoReadRepository;
    private final VideoWriteRepository videoWriteRepository;
    private final UserAuth userAuth;
    private final VideoAdReadRepository videoAdReadRepository;
    private final AdViewWriteRepository adViewWriteRepository;

    public VideoViewResponseDto createVideoView(VideoViewRequestDto videoViewRequestDto) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoViewRequestDto.getUser_id(), email);

        Video video = videoReadRepository.findById(videoViewRequestDto.getVideo_id()).orElseThrow(
                () -> new RuntimeException("기록을 조회할 영상이 존재하지 않습니다.")
        );

        int beforeWatched = videoViewReadRepository.findLatestSavePoint(user.getId(), video.getId()).orElse(0);
        int afterWatched = videoViewRequestDto.getSave_point();


        List<VideoAd> videoAdList = videoAdReadRepository.findAllByVideoId(video.getId());

        if (!video.getUser().getId().equals(user.getId())) {

            // 비디오 업로더와 동영상을 시청한 사람이 다르면, 동영상 조회수 증가
            video.increaseViewCount(video.getView_count());

            // 동영상에 붙은 광고 위치들을 재생 시점과 비교하며 동영상의 광고 조회수 증가 + AdView 객체 생성
            for (VideoAd videoAd : videoAdList) {
                if (beforeWatched < videoAd.getAd_position() && afterWatched >= videoAd.getAd_position()) {
                    videoAd.videoAdCount(videoAd.getView_count());
                    AdView adView = new AdView(LocalDate.now() ,user, videoAd);
                    adViewWriteRepository.save(adView);

                    /*
                    영속성 컨텍스트에 등록된 객체에 한하여 더티체킹이 이루어지기 때문에 save 메서드를 호출할 필요가 없다.
                    근데 한치의 오류도 없이 더티체킹이 이루어지는지가 궁금하다.
                     */
//                    videoAdRepository.save(videoAd);
                }
            }
        }

        LocalDate date = LocalDate.now();
        VideoView videoView = new VideoView(
                date,
                videoViewRequestDto.getSave_point(),
                video,
                user
        );

        VideoView savedVideoView = videoViewWriteRepository.save(videoView);
        return new VideoViewResponseDto(savedVideoView);
    }

    public void deleteVideoView(Long id) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(id, email);

        Video video = videoReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("찾고자 하는 동영상이 존재하지 않습니다.")
        );

        if (video.getUser().getId().equals(user.getId())) {
            videoWriteRepository.delete(video);
        }
        else {
            throw new RuntimeException("해당 동영상에 대한 삭제 권한이 없는 유저입니다.");
        }
    }
}
