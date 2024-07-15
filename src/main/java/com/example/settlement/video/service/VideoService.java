package com.example.settlement.video.service;

import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.read.UserReadRepository;
import com.example.settlement.video.dto.request.VideoRequestDto;
import com.example.settlement.video.dto.response.VideoResponseDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.repository.read.VideoReadRepository;
import com.example.settlement.video.repository.read.VideoViewReadRepository;
import com.example.settlement.video.repository.write.VideoWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {

    private final VideoReadRepository videoReadRepository;
    private final VideoWriteRepository videoWriteRepository;
    private final UserAuth userAuth;
    private final UserReadRepository userReadRepository;
    private final VideoViewReadRepository videoViewReadRepository;


    public VideoResponseDto createVideo(VideoRequestDto videoRequestDto) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoRequestDto.getUser_id(), email);

        Video video = new Video(
                videoRequestDto.getTitle(),
                videoRequestDto.getPlaying_time(),
                0,
                0,
                user
        );
        videoWriteRepository.save(video);

        return new VideoResponseDto(video);
    }

    public List<VideoResponseDto> getVideoList() {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userReadRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("요청한 유저와 등록된 유저가 일치하지 않습니다.")
        );

        List<Video> videoList = videoReadRepository.findAllByUserId(user.getId());

        List<VideoResponseDto> videoResponseDtoList = new ArrayList<>();
        for (Video video : videoList) {
            VideoResponseDto videoResponseDto = new VideoResponseDto(video);
            videoResponseDtoList.add(videoResponseDto);
        }
        return videoResponseDtoList;
    }

    public VideoResponseDto getVideo(Long id) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userReadRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );

        Video video = videoReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Video not found")
        );

        Integer view_point = videoViewReadRepository.findLatestSavePoint(user.getId(), video.getId()).orElse(null);

        int savepoint = view_point != null ? view_point : 0;

        return new VideoResponseDto(video, savepoint);
    }

    public VideoResponseDto updateVideo(Long id, VideoRequestDto videoRequestDto) {

        String email = userAuth.getAuthenticatedUserEmail();
        User user = userAuth.getUserByIdAndValidate(videoRequestDto.getUser_id(), email);
        Video video = videoReadRepository.findById(id).orElseThrow(
                () -> new RuntimeException("찾고자 하는 동영상이 존재하지 않습니다.")
        );

        video.update(
                videoRequestDto.getTitle() != null ? videoRequestDto.getTitle() : video.getTitle(),
                videoRequestDto.getPlaying_time() != video.getPlaying_time() ? videoRequestDto.getPlaying_time() : video.getPlaying_time(),
                user
        );
        videoWriteRepository.save(video);

        return new VideoResponseDto(video);
    }

    public void deleteVideo(Long id) {

        String email = userAuth.getAuthenticatedUserEmail();

        User user = userReadRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with this email")
        );

        Video video = videoReadRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new RuntimeException("삭제하고자 하는 영상이 존재하지 않습니다.")
        );

        videoWriteRepository.delete(video);
    }
}
