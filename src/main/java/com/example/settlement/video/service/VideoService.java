package com.example.settlement.video.service;

import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.UserRepository;
import com.example.settlement.video.dto.request.VideoRequestDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.repository.VideoRepository;
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

    @Transactional
    public Video createVideo(VideoRequestDto videoRequestDto) {
        /*
        1. jwt에서 email 정보 꺼낸다.
        2. 요청 데이터로 온 유저의 email과 jwt에서 꺼낸 email이 같은지 확인한다.(검증)
        3. 맞으면 video 객체 생성
         */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoRequestDto.getUserId(), email);

        Video video = new Video(
                videoRequestDto.getTitle(),
                videoRequestDto.getPlaying_time()
        );
        video.setUser(user);
        return videoRepository.save(video);
    }

    public List<Video> getVideoList() {
        // jwt에서 꺼낸 email로 유저를 찾고, 유저의 id로 된 모든 video 객체를 return.
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );

        List<Video> videoList = videoRepository.findAllByUserId(user.getId());
        return videoList;
    }

    public Video getVideo(Long id) {
        /*
        1. jwt에서 email 정보를 가져온다.
        2. email 정보로 user를 조회한다.
        3. user의 id와 찾고자 하는 video의 id로 video 객체를 조회한다.
        */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );

        Video video = videoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new RuntimeException("Video not found")
        );
        return video;
    }

    public void deleteVideo(Long id) {
        /*
        1. jwt에서 email 정보를 가져온다.(검증)
        2. email 정보로 User를 찾는다.
        3. User의 id와 video의 id로 video 객체를 찾는다.
        4. 삭제
         */
        String email = userAuth.getAuthenticatedUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );
        Video video = videoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        videoRepository.delete(video);
    }
}
