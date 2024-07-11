package com.example.settlement.video.entity;

import com.example.settlement.common.BaseCreateTimeEntity;
import com.example.settlement.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoView extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_view_id")
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private int save_point; // 최근 재생 시점

    @ManyToOne(fetch = FetchType.LAZY) // 실무에서 모든 연관관계는 지연 로딩으로 해두어야 함. 즉시 로딩이 걸려있으면 성능 최적화가 어려움
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public VideoView(LocalDate date, int save_point, Video video, User user) {
        this.date = date;
        this.save_point = save_point;
        this.video = video;
        this.user = user;
    }

    public void updateSavePoint(int save_point) {
        this.save_point = save_point;
    }
}
