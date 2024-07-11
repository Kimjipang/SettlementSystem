package com.example.settlement.advertisement.service;


import com.example.settlement.advertisement.dto.request.AdAdjustmentRequestDto;
import com.example.settlement.advertisement.dto.response.AdAdjustmentResponseDto;
import com.example.settlement.advertisement.entity.AdAdjustment;
import com.example.settlement.advertisement.entity.VideoAd;
import com.example.settlement.advertisement.repository.AdAdjustmentRepository;
import com.example.settlement.advertisement.repository.VideoAdRepository;
import com.example.settlement.common.UserAuth;
import com.example.settlement.user.entity.User;
import com.example.settlement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AdAdjustmentService {

    private final AdAdjustmentRepository adAdjustmentRepository;
    private final VideoAdRepository videoAdRepository;
    private final UserAuth userAuth;
    private final UserRepository userRepository;

    public AdAdjustmentResponseDto calculateDailyAd(AdAdjustmentRequestDto adAdjustmentRequestDto) {
        String email = userAuth.getAuthenticatedUserEmail();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("해당 유저가 존재하지 않습니다.")
        );

        VideoAd videoAd = videoAdRepository.findById(adAdjustmentRequestDto.getVideoAd_id()).orElseThrow(
                () -> new RuntimeException("정산할 광고가 존재하지 않습니다.")
        );

        int ad_accumulate_count = videoAd.getView_count();
        int ad_today_count = adAdjustmentRepository.countTodayAdView(videoAd.getId(), user.getId(), LocalDate.now());


        BigDecimal ad_daily_amount = calculateDailyAmount(
                ad_accumulate_count,
                ad_today_count,
                new BigDecimal("10"),
                new BigDecimal("12"),
                new BigDecimal("15"),
                new BigDecimal("20")
        );
        System.out.println("광고 누적 조회수는 " + ad_accumulate_count);
        System.out.println("광고 당일 조회수는 " + ad_today_count);
        System.out.println("당일 광고 정산 금액은 " + ad_daily_amount);

        AdAdjustment adAdjustment = new AdAdjustment(LocalDate.now(), ad_daily_amount, videoAd);
        adAdjustmentRepository.save(adAdjustment);

        return new AdAdjustmentResponseDto(adAdjustment);

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