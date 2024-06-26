package com.example.settlement.video.entity;


import com.example.settlement.common.BaseEntity;
import com.example.settlement.common.BaseTimeEntity;
import com.example.settlement.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Video extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int playing_time;

    @ManyToOne(fetch = FetchType.LAZY) // 실무에서 모든 연관관계는 지연 로딩으로 해두어야 함. 즉시 로딩이 걸려있으면 성능 최적화가 어려움
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "video")
    private List<VideoView> videoViewList = new ArrayList<>();

    @OneToMany(mappedBy = "video")
    private List<VideoStatistics> videoStatisticsList = new ArrayList<>();

    public Video(String title, int playing_time) {
        this.title = title;
        this.playing_time = playing_time;
    }

    public void update(String title, int playing_time) {
        this.title = title;
        this.playing_time = playing_time;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
