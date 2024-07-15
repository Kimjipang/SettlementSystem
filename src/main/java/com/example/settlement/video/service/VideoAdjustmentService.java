package com.example.settlement.video.service;

import com.example.settlement.video.dto.request.VideoAdjustmentRequestDto;
import com.example.settlement.video.dto.response.VideoAdjustmentResponseDto;
import com.example.settlement.video.entity.Video;
import com.example.settlement.video.entity.VideoAdjustment;
import com.example.settlement.video.repository.read.VideoReadRepository;
import com.example.settlement.video.repository.read.VideoAdjustmentReadRepository;
import com.example.settlement.video.repository.read.VideoViewReadRepository;
import com.example.settlement.video.repository.write.VideoAdjustmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoAdjustmentService {

    private final VideoAdjustmentReadRepository videoAdjustmentReadRepository;
    private final VideoAdjustmentWriteRepository videoAdjustmentWriteRepository;
    private final VideoViewReadRepository videoViewReadRepository;

    private static final LocalDate yesterday = LocalDate.now().minusDays(1);
    private static final LocalDate today = LocalDate.now();

    private final VideoReadRepository videoReadRepository;

    public VideoAdjustmentResponseDto calculateDailyVideo(VideoAdjustmentRequestDto videoAdjustmentRequestDto) {

        Video video = videoReadRepository.findById(videoAdjustmentRequestDto.getVideo_id()).orElseThrow(
                () -> new RuntimeException("찾는 동영상이 존재하지 않습니다.")
        );

//        List<VideoView> videoViewList = videoViewRepository.findAllByVideoIdAndDate(video.getId(), today);

        int video_accumulate_count = video.getView_count(); // 누적 조회수
        int video_today_count = videoViewReadRepository.countByVideoIdAndDateAndDifferentUser(video.getId(), yesterday); // 당일 조회수

        BigDecimal video_daily_amount = calculateDailyAmount(
                video_accumulate_count,
                video_today_count,
                new BigDecimal("1.0"),
                new BigDecimal("1.1"),
                new BigDecimal("1.3"),
                new BigDecimal("1.5")
        );

        VideoAdjustment videoAdjustment = new VideoAdjustment(
                video_daily_amount,
                yesterday,
                video
        );

        System.out.println("동영상 누적 조회수는 " + video_accumulate_count);
        System.out.println("동영상 당일 조회수는 " + video_today_count);
        System.out.println("동영상의 당일 조회수 정산 금액은 " + video_daily_amount);

        VideoAdjustment saved = videoAdjustmentWriteRepository.save(videoAdjustment);

        return new VideoAdjustmentResponseDto(saved);
    }

    public BigDecimal calculateDailyAmount(
            int accumulate_count,
            int today_count,
            BigDecimal under_100K,
            BigDecimal under_500K,
            BigDecimal under_1M,
            BigDecimal over_1M
    ) {
        BigDecimal daily_amount = BigDecimal.ZERO;

        // 정산하려는 날짜가 첫 정산날인 경우
        if(accumulate_count - today_count == 0) {
            // 100만 이상인 경우
            if (today_count >= 1000000) {
                System.out.println(1);
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(400000)))
                        .add(under_1M.multiply(new BigDecimal(500000)))
                        .add(over_1M.multiply(new BigDecimal(today_count - 999999)));
            }
            // 50만 이상 100만 미만인 경우
            else if (today_count >= 500000 && today_count < 1000000) {
                System.out.println(2);
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(400000)))
                        .add(under_1M.multiply(new BigDecimal(today_count - 499999)));
            }
            // 10만 이상 50만 미만인 경우
            else if (today_count >= 100000 && today_count < 500000) {
                System.out.println(3);
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(today_count - 99999)));
            }
            // 10만 미만인 경우
            else {
                System.out.println(4);
                daily_amount = under_100K.multiply(new BigDecimal(today_count));
            }
        }
        // 누적 조회수가 존재하는 경우(정산하려는 날이 첫 정산 날이 아닌 경우)
        else {
            // 누적 조회수 - 당일 조회수가 100만 이상인 경우(당일을 제외한 조회수가 100만 이상)
            if (accumulate_count - today_count >= 1000000) {
                System.out.println(5);
                daily_amount = over_1M.multiply(new BigDecimal(today_count));
            }
            // 누적 조회수 - 당일 조회수가 50만 이상 100만 미만인 경우
            else if (accumulate_count - today_count >= 500000 && accumulate_count - today_count < 1000000) {
                // 당일 조회수를 포함한 조회수가 100만 미만인 경우
                if (accumulate_count < 1000000) {
                    System.out.println(6);
                    daily_amount = under_1M.multiply(new BigDecimal(today_count));
                }
                // 당일 조회수를 포함한 조회수가 100만 이상인 경우
                else {
                    System.out.println(7);
                    daily_amount = under_1M.multiply(new BigDecimal(1000000 - (accumulate_count - today_count) - 1))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
            // 당일 제외 누적 조회수가 10만 이상 50만 미만인 경우
            else if (accumulate_count - today_count >= 100000 && accumulate_count - today_count < 500000) {
                // 당일 포함 조회수가 10만이상 50만인 경우
                if (accumulate_count >= 100000 && accumulate_count < 500000) {
                    System.out.println(8);
                    daily_amount = under_500K.multiply(new BigDecimal(today_count));
                }
                // 당일 포함 조회수가 50만 이상 100만 미만인 경우
                else if (accumulate_count >= 500000 && accumulate_count < 1000000) {
                    System.out.println(9);
                    daily_amount = under_500K.multiply(new BigDecimal(500000 - (accumulate_count - today_count) - 1))
                            .add(under_1M.multiply(new BigDecimal(today_count - (500000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 100만 이상인 경우
                else {
                    System.out.println(10);
                    daily_amount = under_500K.multiply(new BigDecimal(500000 - (accumulate_count - today_count) - 1))
                            .add(under_1M.multiply(new BigDecimal(500000)))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
            // 당일 제회 조회수가 10만 미만인 경우
            else {
                // 당일 포함 조회수가 10만 미만인 경우
                if(accumulate_count < 100000) {
                    System.out.println(11);
                    daily_amount = under_100K.multiply(new BigDecimal(today_count));
                }
                // 당일 포함 조회수가 10만 이상 50만 미만인 경우
                else if (accumulate_count >= 100000 && accumulate_count < 500000) {
                    System.out.println(12);
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(today_count - (100000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 50만 이상 100만 미만인 경우
                else if (accumulate_count >= 500000 && accumulate_count < 1000000) {
                    System.out.println(13);
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(400000)))
                            .add(under_1M.multiply(new BigDecimal(today_count - (500000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 100만 이상인 경우
                else {
                    System.out.println(14);
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(400000)))
                            .add(under_1M.multiply(new BigDecimal(500000)))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
        }
        return daily_amount.setScale(1, RoundingMode.HALF_UP);
    }

}
