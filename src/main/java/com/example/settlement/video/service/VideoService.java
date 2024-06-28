package com.example.settlement.video.service;

import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.UserRepository;
import com.example.settlement.video.dto.request.VideoRequestDto;
import com.example.settlement.video.dto.response.VideoResponseDto;
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
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserAuth userAuth;
    private final UserRepository userRepository;
    private final VideoViewRepository videoViewRepository;

    @Transactional
    public Video createVideo(VideoRequestDto videoRequestDto) {
        /*
        동영상 업로드 API

        1. jwt에서 email 정보 꺼낸다.
        2. 요청 데이터로 온 유저의 email과 jwt에서 꺼낸 email이 같은지 확인한다.(검증)
        3. 맞으면 video 객체 생성
         */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoRequestDto.getUser_id(), email);

        Video video = new Video(
                videoRequestDto.getTitle(),
                videoRequestDto.getPlaying_time(),
                user
        );
        return videoRepository.save(video);
    }

    public List<Video> getVideoList() {
        // jwt에서 꺼낸 email로 유저를 찾고, 유저의 id로 된 모든 video 객체를 return.
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("요청한 유저와 등록된 유저가 일치하지 않습니다.")
        );


        List<Video> videoList = videoRepository.findAllByUserId(user.getId());
        return videoList;
    }

    public VideoResponseDto getVideo(Long id) {
        /*
        1. Video 단일 객체를 조회하면,
        */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );

        Video video = videoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Video not found")
        );

        VideoView videoView = videoViewRepository.findByVideoAndUser(video, user).orElse(null);

        int savepoint = videoView!= null ? videoView.getSave_point() : 0;

        return new VideoResponseDto(video, savepoint);
    }

    @Transactional
    public Video updateVideo(Long id, VideoRequestDto videoRequestDto) {
        /*
        1. jwt 검증
        2. jwt 정보와 요청한 사용자 정보가 동일하면 update 허용
         */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoRequestDto.getUser_id(), email);
        Video video = videoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("찾고자 하는 동영상이 존재하지 않습니다.")
        );

        video.update(
                videoRequestDto.getTitle() != null ? videoRequestDto.getTitle() : video.getTitle(),
                videoRequestDto.getPlaying_time() != video.getPlaying_time() ? videoRequestDto.getPlaying_time() : video.getPlaying_time(),
                user
        );

        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        /*
        1. jwt에서 email 정보를 가져온다.(검증)
        2. email 정보로 User를 찾는다.
        3. User의 id와 video의 id로 video 객체를 찾는다.
        4. 삭제
         */
        String email = userAuth.getAuthenticatedUserEmail();
        /*
        1. jwt에서 email을 꺼내고, 해당 email로 user_id를 찾는다. 찾은 user의 email과 jwt에서 꺼낸 email을 비교해서 같은지 확인
        2. jwt에서 email 꺼내고, 해당 email로 user를 찾는다. 찾은 user의 id와 video_id로 video를 찾는다.
         */
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );
        Video video = videoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new RuntimeException("삭제하고자 하는 영상이 존재하지 않습니다.")
        );
        videoRepository.delete(video);
    }
}
